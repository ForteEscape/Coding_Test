import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] data = new int[N];
    st = new StringTokenizer(br.readLine());

    data[0] = Integer.parseInt(st.nextToken());

    for (int i = 1; i < N; i++){
      data[i] = data[i - 1] + Integer.parseInt(st.nextToken());
    }

    int ans = data[K - 1];
    for (int i = 1; i <= N - K; i++){
      ans = Math.max(ans, data[i + K - 1] - data[i - 1]);
    }

    System.out.println(ans);
  }
}
