import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] price = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        dp[1] = price[1];
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + price[j]);
            }
        }

        System.out.println(dp[N]);
    }
}