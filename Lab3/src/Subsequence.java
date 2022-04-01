import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Subsequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] sequence = new long[n];
        int[] p = new int[n], d = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = in.nextLong();
        }
        Arrays.fill(d, 1);
        Arrays.fill(p, -1);
        int maxL = 1;
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[i] > sequence[j] && d[i] < d[j] + 1) {
                    d[i] = d[j] + 1;
                    p[i] = j;
                }
                if (d[i] > maxL) {
                    maxL = d[i];
                    index = i;
                }
            }
        }
        System.out.println(maxL);
        LinkedList<String> way = new LinkedList<>();
        while (index != -1) {
            way.addFirst(sequence[index] + " ");
            index = p[index];
        }
        for (String s : way) {
            System.out.print(s);
        }
    }
}
