import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());

    int currentBit = m;
    int ans = 0;

    if (M - m < T) {
      System.out.println(-1);
      return;
    }

    while(N > 0) {
      if (currentBit + T <= M) {
        currentBit += T;
        N--;
      } else {
        currentBit = Math.max(m, currentBit - R);
      }

      ans++;
    }

    System.out.println(ans);
  }
}
