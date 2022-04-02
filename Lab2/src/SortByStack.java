import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SortByStack {
    public static void main(String[] args) {
        final String PUSH = "push", POP = "pop";
        Scanner sc = new Scanner(System.in);
        StringBuilder actions = new StringBuilder();
        Stack<Integer> st = new Stack<>();
        int n = sc.nextInt(), x, index = 0;
        int[] ans = new int[n], arr = new int[n];
        for (int i = 0; i < n; i++) {
            x = sc.nextInt();
            arr[i] = x;
            if (st.empty()) {
                st.push(x);
                actions.append(PUSH);
                actions.append("\n");
            } else if (st.peek() > x) {
                st.push(x);
                actions.append(PUSH);
                actions.append("\n");
            } else {
                while (!st.empty() && st.peek() < x) {
                    ans[index++] = st.pop();
                    actions.append(POP);
                    actions.append("\n");
                }
                st.push(x);
                actions.append(PUSH);
                actions.append("\n");
            }
        }
        while (!st.empty()) {
            ans[index++] = st.pop();
            actions.append(POP);
            actions.append("\n");
        }
        Arrays.sort(arr);
        if (Arrays.equals(ans, arr)) {
            System.out.println(actions.toString());
        } else {
            System.out.println("impossible");
        }

    }
}
