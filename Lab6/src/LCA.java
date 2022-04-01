import java.io.PrintWriter;
import java.util.Scanner;

public class LCA {
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

    public static int getDistance(int[] parents, int[] distance, int i) {
        if (distance[parents[i]] == 0 && parents[i] != 1) {
            distance[parents[i]] = getDistance(parents, distance, parents[i]);
        }
        return distance[parents[i]];
    }

    public static int lca(int[] distance, int[] parents, int[][] jump, int v, int u, int k) {
        if (distance[v] > distance[u]) {
            int temp = v;
            v = u;
            u = temp;
        }
        for (int i = k; i >= 0; i--) {
            if (distance[jump[u][i]] - distance[v] >= 0) {
                u = jump[u][i];
            }
        }
        if (v == u) {
            return v;
        }
        for (int i = k; i >= 0; i--) {
            if (jump[v][i] != jump[u][i]) {
                v = jump[v][i];
                u = jump[u][i];
            }
        }
        return parents[v];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = (int) (Math.log(n) / Math.log(2)) + 1;
        int[] parents = new int[n + 1];
        int[] distance = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            parents[i] = in.nextInt();
        }
        parents[1] = 1;
        for (int i = 2; i <= n; i++) {
            distance[i] = getDistance(parents, distance, i) + 1;
        }
        int[][] jump = getJump(parents, n, k);
        int m = in.nextInt();
        PrintWriter writer = new PrintWriter(System.out);
        for (int i = 0; i < m; i++) {
            writer.write(lca(distance, parents, jump, in.nextInt(), in.nextInt(), k) + "\n");
        }
        writer.flush();
    }
}
