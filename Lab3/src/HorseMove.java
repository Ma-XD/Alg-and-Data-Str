import java.util.Arrays;
import java.util.Scanner;

public class HorseMove {
    public static long sum(int n, long[] d) {
        long[] temp = new long[10];
        for (int i = 1; i < n; i++) {
            temp[0] = d[4] + d[6];
            temp[1] = d[8] + d[6];
            temp[2] = d[7] + d[9];
            temp[3] = d[4] + d[8];
            temp[4] = d[9] + d[3] + d[0];
            temp[6] = d[1] + d[7] + d[0];
            temp[7] = d[4] + d[8];
            temp[8] = d[1] + d[3];
            temp[9] = d[2] + d[4];
            for (int j = 0; j < 10; j++) {
                d[j] = temp[j] % 1000000000;
            }
        }
        long sum = 0;
        for (int i = 1; i < 8; i++) {
            sum += d[i];
        }
        sum += d[9];
        return sum % 1000000000;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] d = new long[10];
        Arrays.fill(d, 1);
        System.out.println(sum(n, d));
    }
}
