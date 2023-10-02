import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    StringTokenizer st2 = new StringTokenizer(br.readLine());

    int[][] dp = new int[N + 1][100];
    int[] w = new int[N + 1];
    int[] j = new int[N + 1];

    for (int i = 1; i <= N; i++){
      w[i] = Integer.parseInt(st.nextToken());
      j[i] = Integer.parseInt(st2.nextToken());
    }

    for (int i = 1; i <= N; i++){
      for (int k = 1; k <= 99; k++){
        if (w[i] <= k){
          dp[i][k] = Math.max(dp[i - 1][k - w[i]] + j[i], dp[i - 1][k]);
        } else{
          dp[i][k] = dp[i - 1][k];
        }
      }
    }

    System.out.println(dp[N][99]);
  }
}
