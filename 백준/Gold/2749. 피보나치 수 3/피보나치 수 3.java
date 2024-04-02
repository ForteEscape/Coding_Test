import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static long[] dp;
	static final int MOD = 1_000_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		N = N % (15 * MOD / 10);
		dp = new long[(int)N + 2];
		
		dp[1] = 0;
		dp[2] = 1;
		
		for(int i = 3; i <= N + 1; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
			dp[i] %= MOD;
		}
		
		System.out.println(dp[(int)N + 1]);
	}
}