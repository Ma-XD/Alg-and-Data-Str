import java.util.Scanner;

public class DSU {
    public static int get(int[] p, int x) {
        while (p[x] != x) {
            x = p[x];
        }
        return x;
    }

    public static int getMin(int[] p, int[] min, int x) {
        while (p[x] != x) {
            x = p[x];
        }
        return min[x];
    }

    public static int getMax(int[] p, int[] max, int x) {
        while (p[x] != x) {
            x = p[x];
        }
        return max[x];
    }

    public static int getNumbers(int[] p, int[] count, int x) {
        while (p[x] != x) {
            x = p[x];
        }
        return count[x];
    }

    public static void union(int[] p, int[] r, int[] max, int[] min, int[] count, int x, int y) {
        x = get(p, x);
        y = get(p, y);
        if (y == x) {
            return;
        }
        if (r[x] > r[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        if ( max[x] > max[y]) {
            max[y] = max[x];
        }
        if (min[x] < min[y]) {
            min[y] = min[x];
        }
        count[y] += count[x];
        p[x] = y;
        if (r[x] == r[y]) {
            r[y]++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() + 1, x;
        int[] p = new int[n], r = new int[n], max = new int[n], min = new int[n], count = new int[n];

        for (int i = 0; i < n; i++) {
            r[i] = 0;
            p[i] = i;
            min[i] = i;
            max[i] = i;
            count[i] = 1;
        }
        while (sc.hasNext()) {
            if(sc.next().equals("get")) {
                x = sc.nextInt();
                System.out.println(getMin(p, min, x) + " " + getMax(p, max, x) + " " + getNumbers(p, count, x));
            } else {
                union(p, r, max, min, count, sc.nextInt(), sc.nextInt());
            }
        }
    }
}
