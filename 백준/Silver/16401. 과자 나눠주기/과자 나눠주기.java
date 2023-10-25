import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int[] data = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(data);
    int left = 1;
    int right = data[N - 1];

    int ans = 0;

    while (left <= right) {
      int mid = (left + right) / 2;
      long result = 0L;

      for (int i = 0; i < N; i++) {
        result += (data[i] / mid);

        if (result >= M) {
          break;
        }
      }

      if (result < M) {
        right = mid - 1;
      } else {
        ans = Math.max(ans, mid);
        left = mid + 1;
      }
    }

    if (ans == 0) {
      System.out.println(0);
    } else {
      System.out.println(ans);
    }
  }
}