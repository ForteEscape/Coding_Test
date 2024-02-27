import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long[][] dp = new long[4][1_000_001];
		int MOD = 1_000_000_009;
		
		
		// 3 => 1 + 1 + 1, 1 + 2, 3, 2 + 1
		// dp[i][j] = j를 j - i 에서 i를 더하는 경우의 수
		dp[1][1] = 1;
		
		dp[1][2] = 1;
		dp[2][2] = 1;
		
		//
		dp[1][3] = 1;
		dp[2][3] = 2;
		dp[3][3] = 1;
		
		for(int i = 4; i <= 1_000_000; i++) {
			dp[1][i] = (dp[1][i - 1] % MOD + dp[2][i - 1] % MOD + dp[3][i - 1] % MOD) % MOD;
			dp[2][i] = (dp[1][i - 2] % MOD + dp[2][i - 2] % MOD + dp[3][i - 2] % MOD) % MOD;
			dp[3][i] = (dp[1][i - 3] % MOD + dp[2][i - 3] % MOD + dp[3][i - 3] % MOD) % MOD;
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			System.out.println(((dp[1][N] + dp[2][N]) % MOD + dp[3][N]) % MOD);
		}
	}
	
}