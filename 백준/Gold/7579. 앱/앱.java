import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;

        int[] value = new int[N];
        int[] weight = new int[N];
        int[][] dp = new int[N][10001];

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            value[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j <= 10000; j++){
                if (i == 0) {
                    if (j >= weight[i]) dp[i][j] = value[i];
                } else{
                    if (j >= weight[i]){
                        dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + value[i], dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

                if (dp[i][j] >= M){
                    ans = Math.min(ans, j);
                }
            }
        }

        System.out.println(ans);
    }
}
