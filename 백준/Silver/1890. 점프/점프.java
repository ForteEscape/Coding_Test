import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][N + 1];
        int[][] map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = 1;
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++){
                if (i == N && j == N){
                    break;
                }

                if (dp[i][j] != 0){
                    if (i + map[i][j] <= N){
                        dp[i + map[i][j]][j] += dp[i][j];
                    }

                    if (j + map[i][j] <= N){
                        dp[i][j + map[i][j]] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[N][N]);
    }
}