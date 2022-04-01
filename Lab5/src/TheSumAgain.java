import java.util.Scanner;

public class TheSumAgain {
    private static Node root;

    private static class Node {
        Node left;
        Node right;
        long key;
        long sum;
        long min;
        long max;
        long height;

        private Node(long key) {
            this.key = key;
            this.sum = key;
            this.min = key;
            this.max = key;
            this.height = 1;
        }

        private void recalculate() {
            height = Math.max(getHeight(left), getHeight(right)) + 1;
            sum = key + getSum(left) + getSum(right);
            max = right == null ? key : right.max;
            min = left == null ? key : left.min;
        }

        @Override
        public String toString() {
            return "key: " + key + " sum: " + sum + " height: " + getHeight(this);
        }
    }

    private static long getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private static long getSum(Node node) {
        return node == null ? 0 : node.sum;
    }

    private static void rotate(Node first, Node second) {
        if (second == first.right) {
            first.right = second.left;
            second.left = first;
        } else {
            first.left = second.right;
            second.right = first;
        }
        first.recalculate();
        second.recalculate();
    }

    private static Node add(Node node, long i) {
        if (node == null) {
            return new Node(i);
        }
        if (i == node.key) {
            return node;
        }
        if (i > node.key) {
            node.right = add(node.right, i);
            node.recalculate();
            Node nodeRight = node.right;
            long height = getHeight(node.left);
            if (getHeight(nodeRight.right) == height + 1) {
                rotate(node, nodeRight);
                return nodeRight;
            } else if (getHeight(nodeRight.left) == height + 1) {
                Node nodeRightLeft = nodeRight.left;
                rotate(nodeRight, nodeRightLeft);
                node.right = nodeRightLeft;
                rotate(node, nodeRightLeft);
                return nodeRightLeft;
            }
        } else {
            node.left = add(node.left, i);
            node.recalculate();
            Node nodeLeft = node.left;
            long height = getHeight(node.right);
            if (getHeight(nodeLeft.left) == height + 1) {
                rotate(node, nodeLeft);
                return nodeLeft;
            } else if (getHeight(nodeLeft.right) == height + 1) {
                Node nodeLeftRight = nodeLeft.right;
                rotate(nodeLeft, nodeLeftRight);
                node.left = nodeLeftRight;
                rotate(node, nodeLeftRight);
                return nodeLeftRight;
            }
        }
        return node;
    }

    private static long sum(Node node, long l, long r) {
        if (node == null) {
            return 0;
        }
        if (node.min >= l && node.max <= r) {
            return node.sum;
        }
        if (node.max < l || node.min > r) {
            return 0;
        }
        long q = (node.key >= l && node.key <= r) ? node.key : 0;
        return sum(node.left, l, r) + sum(node.right, l, r) + q;
    }

    private static void printTree(Node node) {
        if (node == null) {
            System.out.println("Empty");
            return;
        }
        System.out.print("root:" + node.key);
        if (node.left != null) {
            System.out.print(" left:(");
            printTree(node.left);
            System.out.print(")");
        }
        if (node.right != null) {
            System.out.print(" right:(");
            printTree(node.right);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long y = 0;
        for (int i = 0; i < n; i++) {
            String op = in.next();
            switch (op) {
                case "+":
                    long key = in.nextLong();
                    root = add(root, (key + y) % 1000000000);
                    y = 0;
                    break;
                case "?":
                    long l = in.nextLong();
                    long r = in.nextLong();
                    y = sum(root, l, r);
                    System.out.println(y);
                    break;
            }
//            printTree(root);
//            System.out.println();
        }
    }
}
