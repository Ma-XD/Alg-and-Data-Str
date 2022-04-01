import java.io.IOException;
import java.util.Stack;

public class Balls {


    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        Stack<Integer> st = new Stack<>();
        int n = sc.nextInt(), k = 0, x = -1, ball1, ball2, i = 0;
        while (i < n){
            if(x == -1) {
                x = sc.nextInt();
            }
            if(st.empty()) {
                st.push(x);
                x = -1;
                i++;
                continue;
            }
            ball1 = st.pop();
            if(st.empty()) {
                st.push(ball1);
                st.push(x);
                x = -1;
                i++;
                continue;
            }
            ball2 = st.pop();
            if (x == ball1 && x == ball2) {
               k += 3;
               if (++i < n) {
                   x = sc.nextInt();
                   while (x == ball1) {
                       k++;
                       if(++i >= n) {
                           break;
                       }
                       x = sc.nextInt();
                   }
               }
            } else {
                st.push(ball2);
                st.push(ball1);
                st.push(x);
                x = -1;
                i++;
            }
        }
        System.out.println(k);
    }
}
