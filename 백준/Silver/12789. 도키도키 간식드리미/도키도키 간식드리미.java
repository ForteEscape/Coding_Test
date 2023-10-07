import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    Deque<Integer> queue = new ArrayDeque<>();
    Deque<Integer> stack = new ArrayDeque<>();
    int currentNumber = 1;

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++){
      queue.addLast(Integer.parseInt(st.nextToken()));
    }

    while(currentNumber <= N){

      // if queue is empty -> if not match current number
      if (!queue.isEmpty()){
        if (queue.peekFirst() != currentNumber){

          if (stack.isEmpty()){
            stack.addLast(queue.pollFirst());
          } else{
            if (queue.peekFirst() < stack.peekLast()){
              stack.addLast(queue.pollFirst());
            } else {
              if (stack.peekLast() == currentNumber){
                stack.pollLast();
                currentNumber++;
              } else{
                System.out.println("Sad");
                return;
              }
            }
          }
        } else{
          queue.pollFirst();
          currentNumber++;
        }
      } else{
        if (stack.peekLast() == currentNumber){
          stack.pollLast();
          currentNumber++;
        } else{
          System.out.println("Sad");
          return;
        }
      }
    }

    System.out.println("Nice");
  }
}
