import java.util.*;

public class VeryEasyTask {

    public static long minTime(int n, int min, int max) {
        long m, left = 0, right = max * (n - 1);
        while (right - left > 1) {
            m = (right + left) / 2;
            if ((m / min + m / max) < n - 1) {
                left = m;
            } else {
                right = m;
            }
        }
        return right + min;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int x = input.nextInt();
        int y = input.nextInt();

        if (x > y) {
            System.out.println(minTime(n, y, x));
        } else {
            System.out.println(minTime(n, x, y));
        }

        input.close();
    }
}
