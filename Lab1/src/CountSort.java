import java.util.*;

public class CountSort {
    public static void digitalSort(int[] arr) {
        int max = 0;
        for (int a : arr) {
            if (a > max) {
                max = a;
            }
        }
        int[] cnt = new int[max + 1];

        for (int k : arr) {
            cnt[k]++;
        }

        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        // System.out.print(Arrays.toString(arr));
        digitalSort(arr);
        input.close();
    }
}