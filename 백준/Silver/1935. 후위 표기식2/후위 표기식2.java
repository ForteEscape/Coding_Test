import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    Map<Character, Double> map = new HashMap<>();

    String formula = br.readLine();
    for (int i = 0; i < N; i++){
      double data = Double.parseDouble(br.readLine());
      map.put((char)('A' + i), data);
    }

    Deque<Double> stack = new ArrayDeque<>();
    for (int i = 0; i < formula.length(); i++){
      char element = formula.charAt(i);

      if (element >= 'A' && element <= 'Z'){
        stack.addLast(map.get(element));
      } else {
        double second = stack.pollLast();
        double first = stack.pollLast();

        if (element == '+'){
          stack.addLast(first + second);
        } else if (element == '-'){
          stack.addLast(first - second);
        } else if (element == '*'){
          stack.addLast(first * second);
        } else if (element == '/'){
          stack.addLast(first / second);
        }
      }
    }

    double result = Math.round(stack.pollLast() * 100.0) / 100.0;
    System.out.println(String.format("%.2f", result));
  }
}
