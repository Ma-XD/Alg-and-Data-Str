import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;

public class Reversals {
    private final static PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
    private static Node root;

    private static class Node {
        final Random random = new Random();
        Node left;
        Node right;
        long size;
        long y;
        long value;
        boolean reverse;

        public Node(long value) {
            this.value = value;
            this.size = 1;
            this.y = random.nextInt();
        }

        @Override
        public String toString() {
            return "value=" + value + " size=" + size;
        }

        public void recalculate() {
            size = getSize(left) + getSize(right) + 1;
        }

        public void propagate() {
            if (reverse) {
                Node temp = left;
                left = right;
                right = temp;
                if (left != null) {
                    left.reverse ^= true;
                }
                if (right != null) {
                    right.reverse ^= true;
                }
                reverse = false;
            }
        }
    }

    private static long getSize(Node node) {
        return node == null ? 0 : node.size;
    }

    private static Node[] split(Node node, long k) {
        if (node == null) {
            return new Node[]{null, null};
        }
        node.propagate();
        if (getSize(node.left) < k) {
            Node[] pair = split(node.right, k - getSize(node.left) - 1);
            node.right = pair[0];
            node.recalculate();
            return new Node[]{node, pair[1]};
        } else {
            Node[] pair = split(node.left, k);
            node.left = pair[1];
            node.recalculate();
            return new Node[]{pair[0], node};
        }
    }

    private static Node merge(Node first, Node second) {
        if (first == null) return second;
        if (second == null) return first;
        first.propagate();
        second.propagate();
        if (first.y > second.y) {
            first.right = merge(first.right, second);
            first.recalculate();
            return first;
        } else {
            second.left = merge(first, second.left);
            second.recalculate();
            return second;
        }
    }

    private static void add(long i, long value) {
        Node[] pair = split(root, i);
        root = merge(pair[0], new Node(value));
        root = merge(root, pair[1]);
    }

    private static void reverse(long l, long r) {
        Node[] pair = split(root, r);
        Node end = pair[1];
        pair = split(pair[0], l - 1);
        Node start = pair[0];
        Node reversed = pair[1];
        reversed.reverse ^= true;
        root = merge(start, reversed);
        root = merge(root, end);
    }

    private static void printTree(Node node) {
        if (node == null) {
            System.out.println();
            return;
        }
        node.propagate();
        if (node.left != null) {
            printTree(node.left);
        }
        writer.write(node.value + " ");
        if (node.right != null) {
            printTree(node.right);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            add(i, i + 1);
        }
        for (int i = 0; i < m; i++) {
            reverse(in.nextInt(), in.nextInt());
        }
        printTree(root);
        writer.flush();
        writer.close();
    }

    public static class Scanner {

        private final BufferedReader source;
        private StringBuilder buff;
        private int buffIndex;
        private int prevIndex;
        private boolean wasPrevInt = false;
        private int prevInt;
        private boolean wasPrevStr = false;
        private String prevStr;
        private int index;

        /**
         * Scanner
         **/

        public Scanner(InputStream source) {
            this.source = new BufferedReader(new InputStreamReader(source));
        }


        public Scanner(String source) {
            this.source = new BufferedReader(new StringReader(source));
        }


        public Scanner(File source) throws IOException {
            this.source = new BufferedReader(new FileReader(source));
        }


        public void close() throws IOException {
            buff = null;
            buffIndex = 0;
            prevIndex = 0;
            source.close();
        }


        /**
         * Supporting methods
         **/

        private void buffCheck() {
            if (buff == null) {
                buff = new StringBuilder();
            } else {
                prevIndex = buffIndex;
            }
        }


        private void indexChek() {
            if (index > 0) {
                buffIndex = --index;
            } else {
                buffIndex = buff.length() - 1;
            }
        }


        private boolean isStr(String str) {
            if (str.length() > 0) {
                prevStr = str;
                wasPrevStr = true;
                return true;
            }
            wasPrevStr = false;
            return false;
        }


        private boolean isInt(String str) {
            try {
                prevInt = Integer.parseInt(str);
                wasPrevInt = true;
                return true;
            } catch (NumberFormatException e) {
                wasPrevInt = false;
                return false;
            }
        }

        /**
         * readInput is a collective method for main scanner's methods
         * for hasNext.. use "true" as argument
         * for next.. use "false" as argument
         * if you want to find in line, use "true" as second argument
         **/

        private String readInput(boolean rememberBuff, boolean inLine) throws IOException {
            wasPrevStr = false;
            wasPrevInt = false;
            StringBuilder str = new StringBuilder();
            boolean buffIsNotEnd = true;
            if (rememberBuff) {
                buffCheck();
            }
            while (true) {
                if ((buff != null) && (buffIsNotEnd)) {
                    if (buffIndex >= buff.length()) {
                        if (rememberBuff) {
                            buffIndex = prevIndex;
                        } else {
                            buff = null;
                            buffIndex = 0;
                        }
                        buffIsNotEnd = false;
                    } else {
                        int read = buff.codePointAt(buffIndex++);
                        if (!(Character.isWhitespace(read)) && (read != '\n')) {
                            str.append((char) read);
                        } else {
                            if ((inLine) && (read == '\n') || (str.length() > 0)) {
                                if (rememberBuff) {
                                    index = buffIndex;
                                    buffIndex = prevIndex;
                                } else {
                                    buffIndex--;
                                }
                                return str.toString();
                            }
                        }
                    }
                } else {
                    int read = source.read();
                    if (read == -1) {
                        buff.append(' ');
                        return str.toString();
                    }
                    if (rememberBuff) {
                        buff.append((char) read);
                    }
                    if (!(Character.isWhitespace(read)) && (read != '\n')) {
                        str.append((char) read);
                    } else {
                        if ((inLine) && (read == '\n') || (str.length() > 0)) {
                            return str.toString();
                        }
                    }
                }
            }
        }


        /** Main methods **/


        /**
         * hasNextInLine, hasNext and next
         **/

        public boolean hasNextInLine() throws IOException {
            String str = readInput(true, true);
            return isStr(str);
        }


        public boolean hasNext() throws IOException {
            String str = readInput(true, false);
            return isStr(str);
        }


        public String next() throws IOException {
            if (wasPrevStr) {
                indexChek();
                wasPrevStr = false;
                return prevStr;
            }
            String str = readInput(false, false);
            if (str.length() > 0) {
                return str;
            } else {
                throw new InputMismatchException();
            }
        }


        /**
         * hasNextIntInLine, hasNextInt and nextInt
         **/

        public boolean hasNextIntInLine() throws IOException {
            String str = readInput(true, true);
            return isInt(str);
        }


        public boolean hasNextInt() throws IOException {
            String str = readInput(true, false);
            return isInt(str);
        }


        public int nextInt() throws IOException {
            if (wasPrevInt) {
                indexChek();
                wasPrevInt = false;
                return prevInt;
            }
            String str = readInput(false, false);
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new InputMismatchException(e.getMessage());
            }
        }


        /**
         * hasNextLine and nextLine
         **/

        public boolean hasNextLine() throws IOException {
            buffCheck();
            if ((buff.length() > 0) && (buff.charAt(buff.length() - 1) == '\n')) {
                return true;
            }
            String res = source.readLine();
            if (res == null) {
                return false;
            }
            buff.append(res).append('\n');
            return true;
        }


        public String nextLine() throws IOException {
            StringBuilder str = new StringBuilder();
            if ((buff != null) && (buff.length() > 0)) {
                str.append(buff.toString());
                buff = null;
                buffIndex = 0;
                if (str.charAt(str.length() - 1) == '\n') {
                    return str.deleteCharAt(str.length() - 1).toString();
                }
            }
            String read = source.readLine();
            if (read == null) {
                return str.toString();
            }
            return str.append(read).toString();
        }


        /**
         * goToNextLine
         **/


        public void goToNextLine() throws IOException {
            if ((buff != null) && (buff.length() > 0)) {
                buffIndex = 0;
                if (buff.charAt(buff.length() - 1) == '\n') {
                    buff = null;
                    return;
                }
                buff = null;
            }
            String read = source.readLine();
        }
    }
}

