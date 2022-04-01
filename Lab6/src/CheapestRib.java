import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CheapestRib {
    private static final int MAX = 10000000;

    public static void getJump(final int[] parents, final int[] cost, int[][] jump, int[][] min, final int n, final int k) {
        for (int i = 1; i <= n; i++) {
            jump[i][0] = parents[i];
            min[i][0] = cost[i];
        }
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                jump[i][j] = jump[jump[i][j - 1]][j - 1];
            }
        }
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                min[i][j] = Math.min(min[i][j - 1], min[jump[i][j - 1]][j - 1]);
            }
        }
    }

    public static int getDistance(final int[] parents, int[] distance, int i) {
        if (distance[parents[i]] == 0 && parents[i] != 1) {
            distance[parents[i]] = getDistance(parents, distance, parents[i]);
        }
        return distance[parents[i]];
    }

    public static int lca(final int[] distance, final int[] parents, final int[][] jump, int v, int u, final int k) {
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
        int[] cost = new int[n + 1];
        int[] distance = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            parents[i] = in.nextInt();
            cost[i] = in.nextInt();
        }
        cost[1] = MAX;
        parents[1] = 1;
        for (int i = 2; i <= n; i++) {
            distance[i] = getDistance(parents, distance, i) + 1;
        }
        int[][] jump = new int[n + 1][k + 1];
        int[][] min =  new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(min[i], MAX);
        }
        getJump(parents, cost, jump, min, n, k);
        int m = in.nextInt();
        PrintWriter writer = new PrintWriter(System.out);
        for (int i = 0; i < m; i++) {
            int x =  in.nextInt(), y = in.nextInt();
            int lca = lca(distance, parents, jump, x, y, k);
            writer.write(Math.min(getMin(min, jump, lca, x), getMin(min, jump, lca, y)) + "\n");
        }
        writer.flush();
    }

    private static int getMin(final int[][] min, final int[][] jump, final int lca, int v) {
        int min1 = MAX;
        if (v == lca) {
            return min1;
        }
        while (v != lca) {
            int k = 0;
            while (jump[v][k + 1] > lca) {
                k++;
            }
            min1 = Math.min(min1, min[v][k]);
            v = jump[v][k];
        }
        return min1;
    }
}
