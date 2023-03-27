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

        int[] dp = new int[K + 1];
        int[] coins = new int[N];

        dp[0] = 1;
        for (int i = 0; i < N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++){
            for (int j = coins[i]; j <= K; j++){
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        System.out.println(dp[K]);
    }
}