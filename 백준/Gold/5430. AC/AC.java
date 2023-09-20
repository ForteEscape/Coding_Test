import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      String process = br.readLine();
      int N = Integer.parseInt(br.readLine());

      Deque<Integer> deque = new ArrayDeque<>();
      String data = br.readLine();

      StringTokenizer st = new StringTokenizer(data.substring(1, data.length() - 1), ",");

      while (st.hasMoreTokens()) {
        deque.addLast(Integer.parseInt(st.nextToken()));
      }

      int dir = 1;
      boolean flag = false;

      for (int j = 0; j < process.length(); j++) {
        if (process.charAt(j) == 'R') {
          dir *= -1;
        } else if (process.charAt(j) == 'D') {
          if (deque.isEmpty()) {
            System.out.println("error");
            flag = true;
            break;
          }

          if (dir == -1) {
            deque.pollLast();
          } else {
            deque.pollFirst();
          }
        }
      }

      if (flag) {
        continue;
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      int size = deque.size();
      if (dir == -1) {
        for (int j = 0; j < size; j++) {
          if (j == size - 1) {
            sb.append(deque.pollLast());
          } else {
            sb.append(deque.pollLast()).append(",");
          }
        }
      } else {
        for (int j = 0; j < size; j++) {
          if (j == size - 1) {
            sb.append(deque.pollFirst());
          } else {
            sb.append(deque.pollFirst()).append(",");
          }
        }
      }

      System.out.println(sb.append("]"));
    }

  }
}
