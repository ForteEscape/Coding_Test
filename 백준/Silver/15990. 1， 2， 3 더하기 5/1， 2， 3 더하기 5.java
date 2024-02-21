import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int MOD = 1_000_000_009;
		
		long[][] dp = new long[4][100001];
		dp[1][1] = 1L;
		dp[2][2] = 1L;
		dp[1][3] = 1L; dp[2][3] = 1L; dp[3][3] = 1L;
		
		for(int i = 4; i <= 100000; i++) {
			dp[1][i] = (dp[2][i - 1] + dp[3][i - 1]) % MOD;
			dp[2][i] = (dp[1][i - 2] + dp[3][i - 2]) % MOD;
			dp[3][i] = (dp[1][i - 3] + dp[2][i - 3]) % MOD;
		}
		
		for(int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			System.out.println(((dp[1][N] + dp[2][N]) % MOD + dp[3][N]) % MOD);
		}
		
	}
}