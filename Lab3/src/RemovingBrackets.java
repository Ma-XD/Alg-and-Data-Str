import java.util.Arrays;
import java.util.Scanner;

public class RemovingBrackets {
    public static void print(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static StringBuilder ans(int[][] d, int[][] way, char[] brackets, int l, int r) {
        int len = r - l + 1;
        StringBuilder sb = new StringBuilder();
        if (d[l][r] >= len)
            return sb;
        if (d[l][r] == 0) {
            for (int i = l; i <= r; i++) {
                sb.append(brackets[i]);
            }
            return sb;
        }
        if (way[l][r] == -1) {
            return sb.append(brackets[l]).append(ans(d, way, brackets, l + 1, r - 1)).append(brackets[r]);
        }
        return sb.append(ans(d, way, brackets, l, way[l][r])).append(ans(d, way, brackets, way[l][r] + 1, r));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        char[] brackets = str.toCharArray();
        int n = brackets.length;
        int[][] d = new int[n][n], way = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = i == j ? 1 : 0;
                way[i][j] = -1;
            }
        }
        for (int r = 0; r < n; r++) {
            for (int l = r - 1; l >= 0; l--) {
                if (brackets[l] == '{' && brackets[r] == '}' ||
                        brackets[l] == '[' && brackets[r] == ']' ||
                        brackets[l] == '(' && brackets[r] == ')') {
                    d[l][r] = d[l + 1][r - 1];
                } else {
                    d[l][r] = Integer.MAX_VALUE;
                }
                for (int i = l; i < r; i++) {
                    if (d[l][r] > d[l][i] + d[i + 1][r]) {
                        d[l][r] = d[l][i] + d[i + 1][r];
                        way[l][r] = i;
                    }
                }
            }
        }
        //print(d);
        //print(way);
        System.out.println(ans(d, way, brackets, 0, n - 1));
    }
}
