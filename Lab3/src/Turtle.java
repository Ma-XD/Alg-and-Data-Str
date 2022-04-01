import java.util.LinkedList;
import java.util.Scanner;

public class Turtle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[][] d = new int[n][m], coins = new int[n][m];
        int[][][] p = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                coins[i][j] = in.nextInt();
            }
        }

        p[0][0][0] = -1;
        p[0][0][1] = -1;
        d[0][0] = coins[0][0];
        for (int i = 1; i < n; i++) {
            d[i][0] = d[i - 1][0] + coins[i][0];
            p[i][0][0] = i - 1;
            p[i][0][1] = 0;
        }
        for (int i = 1; i < m; i++) {
            d[0][i] = d[0][i - 1] + coins[0][i];
            p[0][i][0] = 0;
            p[0][i][1] = i - 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]) + coins[i][j];
                if (d[i - 1][j] > d[i][j - 1]) {
                    p[i][j][0] = i - 1;
                    p[i][j][1] = j;
                } else {
                    p[i][j][0] = i;
                    p[i][j][1] = j - 1;
                }
            }
        }

        System.out.println(d[n - 1][m - 1]);
        LinkedList<Character> way = new LinkedList<>();
        int i = n - 1, j = m - 1;
        while (i != 0 || j != 0) {
            if (i > p[i][j][0]) {
                way.addFirst('D');
            } else {
                way.addFirst('R');
            }
            int tempI = i;
            i = p[i][j][0];
            j = p[tempI][j][1];
        }
        for (char c : way) {
            System.out.print(c);
        }
    }
}
