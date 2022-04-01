import java.util.*;
import java.lang.*;

public class GladeOfFirewood {
    public static double f(int Vp, int Vf, double a, double x) {
        double Sp = Math.sqrt(x * x + (1 - a) * (1 - a)), Sf = Math.sqrt((1 - x) * (1 - x) + a * a);
        return Sp / Vp + Sf / Vf;
    }

    public static double search(int Vp, int Vf, double a) {
        double l = 0, r = 1;
        double m1, m2;
        while (r - l > 0.00001) {
            m1 = (2 * l + r) / 3;
            m2 = (l + 2 * r) / 3;
            if (f(Vp, Vf, a, m1) > f(Vp, Vf, a, m2)) {
                l = m1;
            } else {
                r = m2;
            }
        }
        return (r + l) / 2;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int Vp = input.nextInt(), Vf = input.nextInt();
        double a = Double.parseDouble(input.next());
        System.out.println(search(Vp, Vf, a));
        input.close();
    }
}
