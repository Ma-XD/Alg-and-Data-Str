import java.util.*;
import java.lang.*;

public class SquareRootAndSquare {

    public static double binSearch(double c) {
        double left = 0, right = 10000000000.0, x;
        double m;
        for (int i = 0; i < 1000000; i++) {
            m = (right + left) / 2;
            x = m * m + Math.sqrt(m);
            if (x < c) {
                left = m;
            } else {
                right = m;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        double c = input.nextDouble();

        System.out.println(binSearch(c));
        input.close();
    }
}
