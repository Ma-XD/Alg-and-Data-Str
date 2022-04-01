import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Astrograd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Deque<Integer> queue = new ArrayDeque<>();
        int n = sc.nextInt(), action, id;
        final int len = (int) Math.pow(10, 5);
        int[] number = new int[len + 1];
        int head = 0, tail = 0;
        for (int i = 0; i < n; i++) {
            action = sc.nextInt();
            switch (action) {
                case 1:
                    id = sc.nextInt();
                    number[id] = tail++;
                    queue.offer(id);
                    break;
                case 2:
                    head++;
                    queue.poll();
                    break;
                case 3:
                    tail--;
                    queue.pollLast();
                    break;
                case 4:
                    id = sc.nextInt();
                    writer.write(Integer.toString(number[id] - head));
                    writer.write("\n");
                    break;
                case 5:
                    writer.write(queue.peekFirst().toString());
                    writer.write("\n");
                    break;
            }
        }
        writer.flush();
        writer.close();
    }
}
