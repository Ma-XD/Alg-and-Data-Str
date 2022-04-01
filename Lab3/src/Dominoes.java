import java.util.Arrays;
import java.util.Scanner;

public class Dominoes {
    public static void print(long[][] arr) {
        for (long[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        char[][] field = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            field[i] = s.toCharArray();
        }
        int l = (int) Math.pow(2, n);
        long[][][] d = new long[m + 1][n][l];
        d[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int p = 0; p < l; p++) {
                    int col = i + (j + 1) / n;
                    int row = (j + 1) % n;
                    if ((p & (1 << j)) != 0 || field[j][i] == 'X') {
                        d[col][row][(p | (1 << j)) - (1 << j)] += d[i][j][p];
                    } else {
                        if (i < m - 1 && field[j][i + 1] == '.') {
                            d[col][row][p + (1 << j)] += d[i][j][p];
                        }
                        if (j < n - 1 && (p & (1 << (j + 1))) == 0 && field[j + 1][i] == '.') {
                            d[col][row][p + (1 << (j + 1))] += d[i][j][p];
                        }
                    }
                }
            }
        }
        System.out.println(d[m][0][0]);
    }
}
