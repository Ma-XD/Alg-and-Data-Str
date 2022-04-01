import java.util.*;

public class ApproximateBinarySearch {

    public static int binSearch(int[] a, int x) {
        int left = -1, right = a.length;

        int m;
        while (right - left > 1) {
            m = (right + left) / 2;
            if (a[m] < x) {
                left = m;
            } else if (a[m] > x) {
                right = m;
            } else {
                return m;
            }
        }

        if (left == -1) {
            return right;
        }
        if (right == a.length) {
            return left;
        }
        if (x - left < right - x) {
            return right;
        } else {
            return left;
        }

    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int k = input.nextInt();
        int[] arr = new int[n], values = new int[k];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        for (int i = 0; i < k; i++) {
            values[i] = input.nextInt();
        }

        int l, r;
        for (int i = 0; i < k; i++) {
            System.out.print(arr[binSearch(arr, values[i])] + " ");
        }
        input.close();
    }
}
