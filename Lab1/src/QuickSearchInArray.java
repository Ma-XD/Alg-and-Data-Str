import java.util.*;

public class QuickSearchInArray {
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

    public static int binSearch(int[] a, int l, int r) {
        int leftL = -1, rightL = a.length;
        int leftR = -1, rightR = a.length;
        int m;
        while (rightL - leftL > 1) {
            m = (rightL + leftL) / 2;
            if (a[m] < l) {
                leftL = m;
            } else {
                rightL = m;
            }
        }

        while (rightR - leftR > 1) {
            m = (rightR + leftR) / 2;
            if (a[m] <= r) {
                leftR = m;
            } else {
                rightR = m;
            }
        }
        if (leftL == rightL) {
            return 0;
        }

        return leftR - rightL + 1;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        quickSort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

        int k = input.nextInt();
        int l, r;
        for (int i = 0; i < k; i++) {
            l = input.nextInt();
            r = input.nextInt();
            if (arr[0] > l && arr[n - 1] < r) {
                System.out.print(arr.length + " ");
            } else {
                System.out.print(binSearch(arr, l, r) + " ");
            }
        }
        input.close();
    }
}
