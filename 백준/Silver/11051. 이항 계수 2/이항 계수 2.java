import java.util.*;
import java.io.*;

public class Main {
	
	public static final int MOD = 10007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[1001][1001];
		
		dp[1][0] = 1;
		dp[1][1] = 1;
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0) {
					dp[i][j] = 1;
				} else if(j == i) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % MOD;
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
