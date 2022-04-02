import java.io.PrintWriter;
import java.util.Stack;
import java.io.*;
import java.util.InputMismatchException;

public class MinStack {

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


        /** Supporting methods **/

        private void buffCheck() {
            if (buff == null) {
                buff = new StringBuilder();
            } else {
                prevIndex = buffIndex;
            }
        }


        private void indexChek(){
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

        private String readInput(boolean rememberBuff, boolean inLine) throws  IOException {
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
                        if (rememberBuff){
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
         *  hasNextIntInLine, hasNextInt and nextInt
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
                if (str.charAt(str.length() - 1) == '\n'){
                    return str.deleteCharAt(str.length() - 1).toString();
                }
            }
            String read = source.readLine();
            if (read == null) {
                return str.toString();
            }
            return str.append(read).toString();
        }


        /**  goToNextLine **/


        public void goToNextLine() throws IOException {
            if ((buff != null) && (buff.length() > 0)) {
                buffIndex = 0;
                if (buff.charAt(buff.length() - 1) == '\n'){
                    buff = null;
                    return;
                }
                buff = null;
            }
            String read = source.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Stack<Integer> st = new Stack<>();
        Stack<Integer> stMin = new Stack<>();
        int n = sc.nextInt(), x, operation;
        for (int i = 0; i < n; i++) {
            operation = sc.nextInt();
            if (operation == 1) {
                x = sc.nextInt();
                    st.push(x);
                if (stMin.empty() || (int) stMin.peek() >= x) {
                    stMin.push(x);
                }
            } else if (operation == 2) {
                x = (int) st.pop();
                if (x == (int) stMin.peek()) {
                    stMin.pop();
                }
            } else {
                writer.write(stMin.peek().toString());
                writer.write("\n");
            }
        }
        writer.flush();
        writer.close();
    }
}
