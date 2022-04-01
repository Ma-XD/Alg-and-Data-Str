import java.util.Scanner;

public class AVLTree {
    private static Node root;

    private static class Node {
        Node left;
        Node right;
        long key;
        long height;

        private Node(long key) {
            this.key = key;
            this.height = 1;
        }

        @Override
        public String toString() {
            return "key: " + key + " height: " + getHeight(this);
        }

        private void recalculate() {
            height = Math.max(getHeight(left), getHeight(right)) + 1;
        }
    }

    private static long getHeight(Node node) {
        return node == null ? 0 : node.height;
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

    private static boolean exists(Node node, long x) {
        if (node == null) {
            return false;
        }
        if (node.key == x) {
            return true;
        }
        if (node.key > x) {
            return exists(node.left, x);
        } else {
            return exists(node.right, x);
        }
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

    private static long next(Node node, long x) {
        if (node == null) {
            return Long.MAX_VALUE;
        }
        if (node.key > x) {
            long min = next(node.left, x);
            return Math.min(node.key, min);
        }
        return next(node.right, x);
    }

    private static long prev(Node node, long x) {
        if (node == null) {
            return Long.MIN_VALUE;
        }
        if (node.key < x) {
            long max = prev(node.right, x);
            return Math.max(node.key, max);
        }
        return prev(node.left, x);
    }

    private static void doOperation(String name, long key) {
        long ans;
        switch (name) {
            case "insert":
                root = insert(root, key);
                break;
            case "delete":
                root = delete(root, key);
                break;
            case "exists":
                System.out.println(exists(root, key));
                break;
            case "next":
                ans = next(root, key);
                if (ans < Long.MAX_VALUE) {
                    System.out.println(ans);
                } else {
                    System.out.println("none");
                }
                break;
            case "prev":
                ans = prev(root, key);
                if (ans > Long.MIN_VALUE) {
                    System.out.println(ans);
                } else {
                    System.out.println("none");
                }
                break;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String name = in.next();
            long key = in.nextLong();
            doOperation(name, key);
        }
    }
}