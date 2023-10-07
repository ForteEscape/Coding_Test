import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String data = br.readLine();

    Deque<Character> stack = new ArrayDeque<>();

    for (int i = 0; i < data.length(); i++){
      char element = data.charAt(i);

      if (element == '('){
        stack.addLast(element);
      }

      if (element == ')'){
        if (stack.isEmpty() || stack.peekLast() != '('){
          stack.addLast(element);
        } else {
          stack.pollLast();
        }
      }
    }

    System.out.println(stack.size());
  }
}
