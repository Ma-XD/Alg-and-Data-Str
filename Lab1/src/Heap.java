import java.util.*;

public class Heap {
    static int len = 0;

    public static void Insert(int[] a, int x) {
        a[len++] = x;
        int i = len - 1;
        int temp;

        while ((i > 0) && (a[i] > a[(i - 1) / 2])) {
            temp = a[i];
            a[i] = a[(i - 1) / 2];
            a[(i - 1) / 2] = temp;
            i = (i - 1) / 2;
        }
    }

    public static int Extract(int[] a) {
        int res = a[0];
        a[0] = a[--len];
        int i = 0;
        int temp;
        while (true) {
            int j = i;
            if (2 * i + 1 < len && a[2 * i + 1] > a[j]) {
                j = 2 * i + 1;
            }
            if (2 * i + 2 < len && a[2 * i + 2] > a[j]) {
                j = 2 * i + 2;
            }
            if (j == i) {
                break;
            }
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i = j;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        String command;
        for (int i = 0; i < n; i++) {
            command = input.next();
            if (command.equals("1") && len > 0) {
                System.out.println(Extract(arr));
            } else {
                Insert(arr, input.nextInt());
            }
        }

    }
}
