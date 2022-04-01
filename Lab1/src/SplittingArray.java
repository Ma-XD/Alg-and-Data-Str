import java.util.Scanner;

public class SplittingArray {
    public static boolean good(long[] a, int n, int k, long m) {
        long[] count = new long[k];
        int i = 0, j = 0;
        while (j < n) {
            if (a[j] > m) {
                return false;
            }
            if (count[i] + a[j] > m) {
                if (++i == k) {
                    return false;
                }
            }
            count[i] += a[j++];
        }
        return true;
    }

    public static long search(long[] a, int n, int k) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        long left = sum / k - 1, right = sum, m;
        while (right - left > 1) {
            m = (right + left) / 2;
            if (good(a, n, k, m)) {
                right = m;
            } else {
                left = m;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        //System.out.print(good(array, n, k, 10));
        System.out.print(search(array, n, k));

    }
}
