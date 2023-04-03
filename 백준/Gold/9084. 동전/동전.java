import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] coins = new int[N];

            for (int i = 0; i < N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int K = Integer.parseInt(br.readLine());

            int[] dp = new int[K + 1];
            dp[0] = 1;

            for (int i = 0; i < N; i++){
                for (int j = coins[i]; j <= K; j++){
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }

            System.out.println(dp[K]);
        }
    }
}