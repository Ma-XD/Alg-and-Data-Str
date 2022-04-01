import java.util.Scanner;

public class KMax {
    private static Node root;

    private static class Node {
        Node left;
        Node right;
        long key;
        long height;
        long number;

        private Node(long key) {
            this.key = key;
            this.height = 1;
            this.number = 1;
        }

        @Override
        public String toString() {
            return "key: " + key + " number: " + getNumber(this);
        }

        private void recalculate() {
            height = Math.max(getHeight(left), getHeight(right)) + 1;
            number = getNumber(left) + getNumber(right) + 1;
        }
    }

    private static long getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private static long getNumber(Node node) {
        return node == null ? 0 : node.number;
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

    private static Node insert(Node node, long x) {
        if (node == null) {
            return new Node(x);
        }
        if (x == node.key) {
            return node;
        }
        if (x > node.key) {
            node.right = insert(node.right, x);
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
            node.left = insert(node.left, x);
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

    private static Node delete(Node node, long x) {
        if (node == null) {
            return null;
        }
        if (node.key == x) {
            if (node.right == null && node.left == null) {
                return null;
            }
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            long key = getMin(node).key;
            node.key = key;
            node.right = delete(node.right, key);
        }
        if (node.key > x) {
            node.left = delete(node.left, x);
        } else {
            node.right = delete(node.right, x);
        }
        node.recalculate();
        return node;
    }

    private static Node getMin(Node node) {
        Node min = node.right;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    private static long kMax(Node node, long k) {
        if (node.number - getNumber(node.right) == k) {
            return node.key;
        }
        if (node.number - getNumber(node.right) > k) {
            return kMax(node.left, k);
        } else {
            return kMax(node.right, k - getNumber(node.left) - 1);
        }
    }


    private static void doOperation(int name, long key) {
        switch (name) {
            case 1:
                root = insert(root, key);
                break;
            case -1:
                root = delete(root, key);
                break;
            case 0:
                System.out.println(kMax(root, getNumber(root) - key + 1));
                break;
        }
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
        for (int i = 0; i < n; i++) {
            int name = in.nextInt();
            long key = in.nextLong();
            doOperation(name, key);
//            printTree(root);
//            System.out.println();
        }
    }
}
