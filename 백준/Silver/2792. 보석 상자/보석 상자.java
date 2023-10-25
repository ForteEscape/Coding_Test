import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] colors = new int[M];

    int right = 0;
    for (int i = 0; i < M; i++) {
      colors[i] = Integer.parseInt(br.readLine());
      right = Math.max(right, colors[i]);
    }

    int left = 1;
    int ans = Integer.MAX_VALUE;

    while (left <= right) {
      int mid = (left + right) / 2;
      int sum = 0;

      for (int i = 0; i < M; i++) {
        sum += colors[i] / mid;

        if (colors[i] % mid != 0) {
          sum++;
        }
      }

      if (sum > N) {
        left = mid + 1;
      } else {
        ans = Math.min(ans, mid);
        right = mid - 1;
      }
    }

    System.out.println(ans);
  }
}