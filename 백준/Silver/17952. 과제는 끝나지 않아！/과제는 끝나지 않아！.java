import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int ans = 0;
    Deque<int[]> stack = new ArrayDeque<>();

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int first = Integer.parseInt(st.nextToken());
      if (first == 0) {
        if (!stack.isEmpty()) {
          stack.peekLast()[1]--;

          if (stack.peekLast()[1] == 0) {
            ans += stack.pollLast()[0];
          }
        }
      } else {
        int[] data = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1};

        if (data[1] == 0) {
          ans += data[0];
        } else {
          stack.addLast(data);
        }
      }
    }

    System.out.println(ans);
  }
}
