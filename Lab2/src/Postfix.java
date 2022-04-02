import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Postfix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Long> stack = new Stack<>();
        char operation;
        while (sc.hasNext()) {
            if (sc.hasNextLong()) {
                stack.push(sc.nextLong());
            } else {
                operation = sc.next().charAt(0);
                switch (operation) {
                    case '+':
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case '-':
                        long a = stack.pop(), b = stack.pop();
                        stack.push(b - a);
                        break;
                    case '*':
                        stack.push(stack.pop() * stack.pop());
                        break;
                }
            }
        }
        System.out.println(stack.pop());
    }
}