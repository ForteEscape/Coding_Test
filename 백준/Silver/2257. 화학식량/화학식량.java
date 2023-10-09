import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
  static Map<Character, Integer> map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String data = br.readLine();

    Deque<Integer> stack = new ArrayDeque<>();
    map = new HashMap<>();
    map.put('H', 1);
    map.put('C', 12);
    map.put('O', 16);

    int idx = 0;
    while(idx < data.length()){
      char element = data.charAt(idx);

      if (element == '('){
        idx = solve(idx + 1, data, stack);
        continue;
      }

      if (element >= 'A' && element <= 'Z'){
        stack.addLast(map.get(element));
      } else if (element >= '2' && element <= '9'){
        stack.addLast(stack.pollLast() * (element - '0'));
      }

      idx++;
    }

    int ans = 0;
    while (!stack.isEmpty()){
      ans += stack.pollLast();
    }

    System.out.println(ans);
  }

  static int solve(int idx, String data, Deque<Integer> stack){
    Deque<Integer> tempStack = new ArrayDeque<>();

    while(data.charAt(idx) != ')'){
      if (data.charAt(idx) == '('){
        idx = solve(idx + 1, data, tempStack);
        continue;
      }

      if (data.charAt(idx) >= 'A' && data.charAt(idx) <= 'Z'){
        tempStack.addLast(map.get(data.charAt(idx)));
      } else if (data.charAt(idx) >= '2' && data.charAt(idx) <= '9'){
        tempStack.addLast(tempStack.pollLast() * (data.charAt(idx) - '0'));
      }

      idx++;
    }

    int result = 0;
    while(!tempStack.isEmpty()){
      result += tempStack.pollLast();
    }

    stack.addLast(result);

    return idx + 1;
  }
}
