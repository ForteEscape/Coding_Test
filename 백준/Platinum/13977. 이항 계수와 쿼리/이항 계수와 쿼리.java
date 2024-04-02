import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static final int MOD = 1_000_000_007;
	static final int SIZE = 4_000_001;
	static long[] facto;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		facto = new long[SIZE];
		init();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			long ans = (facto[N] % MOD * (pow(((facto[K] % MOD * facto[N - K] % MOD) % MOD), MOD - 2)) % MOD) % MOD;
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	static long pow(long x, long y) {
		if(y == 0) {
			return 1;
		}
		
		long res = pow(x, y / 2);
		
		if(y % 2 == 0) {
			return (res % MOD * res % MOD) % MOD;
		} else {
			return ((res % MOD * res % MOD) % MOD * x % MOD) % MOD;
		}
	}
	
	static void init() {
		facto[0] = 1;
		facto[1] = 1;
		
		for(int i = 2; i < SIZE; i++) {
			facto[i] = facto[i - 1] * i;
			facto[i] %= MOD;
		}
	}
}