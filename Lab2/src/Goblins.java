import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Goblins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Deque<Integer> left = new ArrayDeque<>(), right = new ArrayDeque<>();
        int n = sc.nextInt(), goblin, leftCount = 0, rightCount = 0;
        String action;
        for (int i = 0; i < n; i++) {
            action = sc.next();
            if (action.equals("-")) {
                writer.write(left.poll().toString());
                writer.write("\n");
                if (!(right.isEmpty() || leftCount > rightCount)) {
                    left.addLast(right.poll());
                    rightCount--;
                } else {
                    leftCount--;
                }
            } else {
                goblin = sc.nextInt();
                if (action.equals("+")) {
                    if(leftCount > rightCount) {
                        right.addLast(goblin);
                        rightCount++;
                    } else if (right.isEmpty()) {
                        left.addLast(goblin);
                        leftCount++;
                    } else {
                        left.addLast(right.poll());
                        right.addLast(goblin);
                        leftCount++;
                    }
                } else if(leftCount > rightCount) {
                    right.addFirst(goblin);
                    rightCount++;
                } else {
                    left.addLast(goblin);
                    leftCount++;
                }
            }
        }
        writer.flush();
        writer.close();
    }
}
