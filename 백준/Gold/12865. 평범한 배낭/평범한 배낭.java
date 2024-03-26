import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] cost = new int[N];
		int[] value = new int[N];
		
		int[][] dp = new int[N][K + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			cost[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= K; j++) {
				if(i == 0) {
					if(j >= cost[i]) {
						dp[i][j] = value[i];
					}
				} else {
					if(j < cost[i]) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + value[i]);
					}
				}
			}
		}
		
		System.out.println(dp[N - 1][K]);
	}
}