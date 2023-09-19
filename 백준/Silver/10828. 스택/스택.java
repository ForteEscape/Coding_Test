import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Deque<Integer> stack = new ArrayDeque<>();

    int N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      String data = br.readLine();

      StringTokenizer st = new StringTokenizer(data);
      String command = st.nextToken();

      if (command.equals("push")) {
        stack.addLast(Integer.parseInt(st.nextToken()));
      } else if (command.equals("top")) {
        if (stack.isEmpty()) {
          System.out.println(-1);
        } else{
          System.out.println(stack.peekLast());
        }
      } else if (command.equals("size")) {
        System.out.println(stack.size());
      } else if (command.equals("pop")){
        if (stack.isEmpty()) {
          System.out.println(-1);
        } else{
          System.out.println(stack.pollLast());
        }
      } else if (command.equals("empty")){
        if (stack.isEmpty()) {
          System.out.println(1);
        } else{
          System.out.println(0);
        }
      }
    }
  }
}
