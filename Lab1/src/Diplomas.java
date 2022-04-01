import java.util.*;
import java.lang.*;

public class Diplomas {
    private static boolean good(long x, long w, long  h, long n){
        return ((x/h) * (x/w) >= n);

    }

    public static long minBoard(long w, long h, long n) {
        long  left = 0, right = Math.max(h, w) * n, m;
        while (right - left > 1) {
            m = (right + left) / 2;
            if (good(m, w, h, n)) {
                right = m;
            } else {
                left = m;
            }
        }
        return right;
    }


    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);


        long w = input.nextInt();
        long h = input.nextInt();
        long n = input.nextInt();


        System.out.println(minBoard(w, h ,n));
        input.close();
    }
}
