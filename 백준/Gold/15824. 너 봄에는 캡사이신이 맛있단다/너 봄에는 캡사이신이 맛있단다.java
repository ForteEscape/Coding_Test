import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	private static int MOD = 1_000_000_007;
	static long[] dp;
	static long[] data;
	static int N;
	static int tmp = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new long[N];
		data = new long[N];
		
		init();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(data);
		
		long p = 0;
		long m = 0;
		for(int i = N - 1; i > 0; i--) {
			p += dp[i] * data[i];
			m += dp[i] * data[N - 1 - i];
			
			p %= MOD;
			m %= MOD;
		}
		
		System.out.println((p % MOD + MOD - m % MOD) % MOD);
	}
	
	static void init() {	
		for(int i = 0; i < N; i++) {
			dp[i] = tmp - 1;
			tmp *= 2;
			tmp %= MOD;
		}
	}
}