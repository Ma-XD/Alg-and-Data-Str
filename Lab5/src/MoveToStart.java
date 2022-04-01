import java.util.Random;
import java.util.Scanner;

public class MoveToStart {
    private static Node root;
    private static class Node {
        final Random random = new Random();
        Node left;
        Node right;
        long size;
        long y;
        long value;

        public Node(long value) {
            this.value = value;
            this.size = 1;
            this.y = random.nextInt();
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", y=" + y +
                    ", value=" + value +
                    ", size=" + size +
                    '}';
        }

        public void recalculate() {
            size = getSize(left) + getSize(right) + 1;
        }
    }

    private static long getSize(Node node) {
        return node == null ? 0 : node.size;
    }

    private static Node[] split(Node node, long k) {
        if (node == null) {
            return new Node[]{null, null};
        }
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

    private static void moveToStart(long l, long r) {
        Node[] pair = split(root, r);
        Node end = pair[1];
        pair = split(pair[0], l - 1);
        root = merge(pair[1], pair[0]);
        root = merge(root, end);
    }

    private static void printTree(Node node) {
        if (node == null) {
            System.out.println();
            return;
        }
        if (node.left != null) {
            printTree(node.left);
        }
        System.out.print(node.value + " ");
        if (node.right != null) {
            printTree(node.right);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            add(i, i + 1);
        }
        for (int i = 0; i < m; i++) {
            moveToStart(in.nextLong(), in.nextLong());
        }
        printTree(root);
    }
}

/*
1 2 3 4 5 6
2 3 4 1 5 6
4 1 5 2 3 6
1 4 5 2 3 6
 */