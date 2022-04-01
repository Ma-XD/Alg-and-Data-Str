import java.util.Scanner;

public class LevenshteinDistance {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] w1 = in.next().toCharArray();
        char[] w2 = in.next().toCharArray();
        char[] s = new char[w1.length + 1], t = new char[w2.length + 1];
        System.arraycopy(w1, 0, s, 1, s.length - 1);
        System.arraycopy(w2, 0, t, 1, t.length - 1);
        long[][] d = new long[s.length][t.length];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < t.length; j++) {
                if (i == 0 || j == 0) {
                    d[i][j] = i + j;
                    continue;
                }
                if (s[i] == t[j]) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.min(
                            Math.min(
                                    d[i - 1][j - 1] + 1,
                                    d[i - 1][j] + 1),
                            d[i][j - 1] + 1);
                }
            }
        }

        System.out.println(d[s.length - 1][t.length - 1]);

    }
}
