import java.util.*;

public class SimpleSort {

    public static void quickSort(int[] arr, int l, int r) {
        int x = arr[(l + r) / 2];

        if (r - l < 1) {
            return;
        }

        int lCnt = l, rCnt = r;
        while (lCnt <= rCnt) {

            while (arr[lCnt] < x) {
                lCnt++;
            }

            while (arr[rCnt] > x) {
                rCnt--;
            }

            if (lCnt <= rCnt) {
                int tmp = arr[lCnt];
                arr[lCnt] = arr[rCnt];
                arr[rCnt] = tmp;
                lCnt++;
                rCnt--;
            }
        }

        quickSort(arr, l, rCnt);
        quickSort(arr, lCnt, r);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        quickSort(arr, 0, arr.length - 1);
        // System.out.print(Arrays.toString(arr));
        for (int a : arr) {
            System.out.print(a + " ");
        }

        input.close();
    }
}

