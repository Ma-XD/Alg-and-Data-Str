import java.util.LinkedList;
import java.util.Scanner;

public class Grasshopper {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] coins = new int[n], way = new int[n];
        long[] d = new long[n];
        for (int i = 1; i < n - 1; i++) {
            coins[i] = in.nextInt();
        }

        for (int i = 1; i < n; i++) {
            d[i] = Long.MIN_VALUE;
            for (int j = 1; j < k + 1; j++) {
                if (i - j >= 0) {
                    long l = d[i - j] + coins[i];
                    if (d[i] <= l) {
                        d[i] = l;
                        way[i] = i - j;
                    }
                }
            }
        }

        System.out.println(d[n - 1]);
        LinkedList<Integer> col = new LinkedList<Integer>();
        int count = 0;
        int i = n - 1;
        while (i > 0) {
            count++;
            col.addFirst(way[i] + 1);
            i = way[i];
        }
        System.out.println(count);
        for (int c : col) {
            System.out.print(c + " ");
        }
        System.out.print(n);
    }
}
