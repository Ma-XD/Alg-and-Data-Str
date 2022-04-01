
import java.io.PrintWriter;
import java.util.Scanner;

public class BinaryClimbs {
    public static int[][] getJump(final int[] parents, final int n, final int k) {
        int[][] jump = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            jump[i][0] = parents[i];
        }
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                jump[i][j] = jump[jump[i][j - 1]][j - 1];
            }
        }
        return jump;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = (int) (Math.log(n) / Math.log(2)) + 1;
        int[] parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = in.nextInt();
        }

            int[][] jump = getJump(parents, n, k);
        PrintWriter writer = new PrintWriter(System.out);
        for (int i = 1; i <= n; i++) {
            writer.write(i + ": ");
            for (int j = 0; j <= k; j++) {
                if (jump[i][j] > 0) {
                    writer.write(jump[i][j] + " ");
                }
            }
            writer.write("\n");
        }
        writer.flush();
    }
}
