import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX = 900_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C + 101];
        Arrays.fill(dp, MAX);

        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for (int j = value; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - value]);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = C; i < C + 101; i++) {
            ans = Math.min(ans, dp[i]);
        }

        System.out.println(ans);
    }
}