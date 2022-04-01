import java.util.*;

public class NumberOfInversions {
    static long count = 0;

    public static int[] merge(int[] a, int[] b) {
        int n = a.length, m = b.length, i = 0, j = 0, k = 0;
        int[] c = new int[n + m];
        while ((i < n) || (j < m)) {
            if ((j == m) || ((i < n) && (a[i] <= b[j]))) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
                count += n - i;
            }
        }
        return c;
    }

    public static int[] sort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return a;
        }
        int[] al, ar;
        al = Arrays.copyOfRange(a, 0, n / 2);
        ar = Arrays.copyOfRange(a, n / 2, n);
        al = sort(al);
        ar = sort(ar);
        return merge(al, ar);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        arr = sort(arr);
        System.out.println(count);
        input.close();
    }
}
