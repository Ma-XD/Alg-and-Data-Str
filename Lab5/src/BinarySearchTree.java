import java.util.Scanner;

public class BinarySearchTree {
    private static Node root;

    private static class Node {
        Node parent;
        Node leftChild;
        Node rightChild;
        long value;

        @Override
        public String toString() {
            return Long.toString(value);
        }
    }

    private static void insert(Node node, long x) {
        if (node.value == x) {
            return;
        }
        if (node.value > x) {
            if (node.leftChild == null) {
                Node leftChild = new Node();
                leftChild.value = x;
                leftChild.parent = node;
                node.leftChild = leftChild;
                return;
            }
            insert(node.leftChild, x);
        } else {
            if (node.rightChild == null) {
                Node rightChild = new Node();
                rightChild.value = x;
                rightChild.parent = node;
                node.rightChild = rightChild;
                return;
            }
            insert(node.rightChild, x);
        }
    }

    private static boolean exists(Node node, long x) {
        if (node == null) {
            return false;
        }
        if (node.value == x) {
            return true;
        }
        if (node.value > x) {
            return exists(node.leftChild, x);
        } else {
            return exists(node.rightChild, x);
        }
    }

    private static void delete(Node node, long x) {
        if (node == null) {
            return;
        }
        if (node.value == x) {
            if (node.rightChild == null && node.leftChild == null) {
                if (node.parent != null) {
                    if (node.parent.rightChild == node) {
                        node.parent.rightChild = null;
                    } else {
                        node.parent.leftChild = null;
                    }
                } else {
                    root = null;
                }
                return;
            }
            if (node.rightChild == null) {
                if (node.parent != null) {
                    if (node.parent.rightChild == node) {
                        node.parent.rightChild = node.leftChild;
                    } else {
                        node.parent.leftChild = node.leftChild;
                    }
                } else {
                    root = node.leftChild;
                }
                node.leftChild.parent = node.parent;
                return;
            }
            if (node.leftChild == null) {
                if (node.parent != null) {
                    if (node.parent.rightChild == node) {
                        node.parent.rightChild = node.rightChild;
                    } else {
                        node.parent.leftChild = node.rightChild;
                    }
                } else {
                    root = node.rightChild;
                }
                node.rightChild.parent = node.parent;
                return;
            }
            Node min = getMin(node);
            node.value = min.value;
            if (min.parent.leftChild == min) {
                min.parent.leftChild = min.rightChild;
            } else {
                min.parent.rightChild = min.rightChild;
            }
            if (min.rightChild != null) {
                min.rightChild.parent = min.parent;
            }
        }
        if (node.value > x) {
            delete(node.leftChild, x);
        } else {
            delete(node.rightChild, x);
        }
    }

    private static Node getMin(Node node) {
        Node min = node.rightChild;
        while (min.leftChild != null) {
            min = min.leftChild;
        }
        return min;
    }

    private static long next(Node node, long x) {
        if (node == null) {
            return Long.MAX_VALUE;
        }
        if (node.value > x) {
            long min = next(node.leftChild, x);
            return Math.min(node.value, min);
        }
        return next(node.rightChild, x);
    }

    private static long prev(Node node, long x) {
        if (node == null) {
            return Long.MIN_VALUE;
        }
        if (node.value < x) {
            long max = prev(node.rightChild, x);
            return Math.max(node.value, max);
        }
        return prev(node.leftChild, x);
    }

    private static void doOperation(String name, long value) {
        long ans;
        switch (name) {
            case "insert":
                if (root == null) {
                    root = new Node();
                    root.value = value;
                    break;
                }
                insert(root, value);
                break;
            case "delete":
                if (exists(root, value)) {
                    delete(root, value);
                }
                break;
            case "exists":
                System.out.println(exists(root, value));
                break;
            case "next":
                ans = next(root, value);
                if (ans < Long.MAX_VALUE) {
                    System.out.println(ans);
                } else {
                    System.out.println("none");
                }
                break;
            case "prev":
                ans = prev(root, value);
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
            long value = in.nextLong();
            doOperation(name, value);
        }
    }
}
