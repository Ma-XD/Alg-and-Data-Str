import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void quickSort(int[] a, int l, int r) {
        if (r - l < 1) {
            return;
        }
        int x = a[(r + l) / 2];

        int lx = l, rx = r;
        while (lx <= rx) {
            while (a[lx] < x) lx++;
            while (a[rx] > x) rx--;

            if (lx <= rx) {
                int t = a[lx];
                a[lx] = a[rx];
                a[rx] = t;
                lx++;
                rx--;
            }
        }

        quickSort(a, l, rx);
        quickSort(a, lx, r);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        int n = in.nextInt();
        int [] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        quickSort(a, 0, a.length - 1);
        for (int ai : a) {
            writer.print(ai + " ");
        }
        writer.flush();
        in.close();
        writer.close();
    }
}
