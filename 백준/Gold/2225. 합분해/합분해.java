import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1]; // K 개의 수로 N을 만드는 경우의 수

        for(int i = 1; i <= K; i++){ // 각각의 K에 대해 0을 만들 수 있는 경우의 수는 0만을 더한 경우의 수 이외엔 없음
            dp[i][0] = 1;
        }

        for (int i = 0; i <= N; i++){ // 각각의 N에 대해 1번 더해 N을 만들 수 있는 경우의 수는 자기 자신 이외엔 없음
            dp[1][i] = 1;
        }

        for (int i = 2; i <= K; i++){
            for (int j = 1; j <= N; j++){
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }

        System.out.println(dp[K][N]);
    }
}