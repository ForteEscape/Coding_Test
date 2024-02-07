import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (Math.abs(x) - Math.abs(y)) == 0 ? x - y : (Math.abs(x) - Math.abs(y)));

    for (int i = 0; i < N; i++) {
      int data = Integer.parseInt(br.readLine());

      if (data == 0) {
        if (pq.isEmpty()) {
          System.out.println(0);
        } else {
          System.out.println(pq.poll());
        }
      } else {
        pq.offer(data);
      }
    }

  }
}