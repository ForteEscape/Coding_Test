import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] weight = new int[M];
		int[] value = new int[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[M][N + 1];
		for(int i = 0; i < M; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == 0) {
					if(j >= weight[i]) {
						dp[i][j] = value[i];
					}
				} else {
					if(j >= weight[i]) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}
		
		System.out.println(dp[M - 1][N]);
	}
}