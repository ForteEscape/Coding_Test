import java.io.*;
import java.util.*;

public class Main {
	
	static long[] ans;
	static long[] dp;
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	
		
		dp = new long[2];
		dp[0] = 1; dp[1] = 1;
		
		for(int i = 2; i < N; i++) {
			dp[i % 2] = (dp[i % 2] + dp[(i + 1) % 2]) % MOD;
		}
		
		
		System.out.println(dp[(N - 1) % 2] % MOD + " " + (N - 2) % MOD);
	}
}