import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int INF = 1000000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] color = new int[N + 1][3];
        int[][] dp = new int[N + 1][3];
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            color[i][0] = Integer.parseInt(st.nextToken());
            color[i][1] = Integer.parseInt(st.nextToken());
            color[i][2] = Integer.parseInt(st.nextToken());
        }

        // 첫 번째 집의 색 정하기
        for (int c = 0; c < 3; c++){
            for (int i = 0; i < 3; i++){
                if (i == c){
                    dp[1][i] = color[1][i];
                } else{
                    dp[1][i] = INF;
                }
            }

            for (int i = 2; i <= N; i++){
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + color[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + color[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + color[i][2];
            }

            for (int i = 0; i < 3; i++){
                if (i == c) continue;

                ans = Math.min(ans, dp[N][i]);
            }
        }

        System.out.println(ans);
    }
}