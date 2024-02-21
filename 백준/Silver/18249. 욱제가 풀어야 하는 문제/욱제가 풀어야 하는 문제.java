import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int MOD = 1_000_000_007;
		
		int[] dp = new int[191230];
		dp[0] = 1; dp[1] = 1;
		
		for(int i = 2; i <= 191229; i++) {
			dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
		}
		
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(dp[N] % MOD);
		}
	}
}