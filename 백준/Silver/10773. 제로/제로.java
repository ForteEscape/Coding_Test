import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Deque<Integer> stack = new ArrayDeque<>();

    int K = Integer.parseInt(br.readLine());

    for (int i = 0; i < K; i++){
      int num = Integer.parseInt(br.readLine());

      if (num == 0){
        stack.pollLast();
      } else{
        stack.addLast(num);
      }
    }
    
    System.out.println(
        stack.stream()
            .mapToInt(element -> element)
            .sum()
    );
  }
}
