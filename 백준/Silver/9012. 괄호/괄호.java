import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int T = 0; T < N; T++){
            String input = br.readLine();
            Deque<Character> stack = new ArrayDeque<>();
            boolean flag = false;

            for (int i = 0; i < input.length(); i++){
                char c = input.charAt(i);

                if (c == '('){
                    stack.addLast(c);
                } else if (c == ')'){
                    if (!stack.isEmpty() && stack.peekLast() == '('){
                        stack.pollLast();
                    } else{
                        flag = true;
                        break;
                    }
                }
            }

            if (!stack.isEmpty()){
                flag = true;
            }

            if (flag){
                System.out.println("NO");
            } else{
                System.out.println("YES");
            }
        }
    }
}