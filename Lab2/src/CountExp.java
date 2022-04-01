import java.util.Scanner;

public class CountExp {
    public static int[] get(int[] p, int[] w, int x) {
        if (x == p[x]) {
            return new int[]{x, 0};
        }
        int[] arr = get(p, w, p[x]);
        p[x] = arr[0];
        w[x] += arr[1];
        return new int[]{p[x], w[x]};
    }

    public static void increaseWeight(int[] p, int[] w, int x, int d) {
        int[] arr = get(p, w, x);
        w[arr[0]] += d;
    }

    public static int findWeight(int[] p, int[] w, int x) {
        int[] arr = get(p ,w, x);
        if (x == p[x]) {
            return w[x];
        }
        return w[x] + w[p[x]];
    }

    public static void union(int[] p, int[] w, int[] r, int x, int y) {
        int[] arrX = get(p ,w, x), arrY = get(p ,w, y);
        x = arrX[0];
        y = arrY[0];
        if (x == y) {
            return;
        }
        if (r[x] > r[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        p[x] = y;
        w[x] -= w[y];
        if (r[x] == r[y]) {
            r[y]++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() + 1, m = sc.nextInt(), x, y, weight;
        int[] p = new int[n], r = new int[n], w = new int[n];
        String action;
        for (int i = 0; i < n; i++) {
            r[i] = 0;
            p[i] = i;
            w[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            action = sc.next();
            if (action.equals("get")) {
                x = sc.nextInt();
                System.out.println(findWeight(p, w, x));
            } else  if(action.equals("add")) {
                x = sc.nextInt();
                weight = sc.nextInt();
                increaseWeight(p, w, x, weight);
            } else {
                x = sc.nextInt();
                y = sc.nextInt();
                union(p, w, r, x, y);
            }
        }
    }
}
