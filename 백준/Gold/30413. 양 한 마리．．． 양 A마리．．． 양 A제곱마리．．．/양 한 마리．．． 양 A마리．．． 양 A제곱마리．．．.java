import java.util.*;
import java.io.*;

class Main {
	
	static long A, B;
	static final int MOD = 1_000_000_007;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		if(A == 1) {
			System.out.println(((A % MOD) * (B % MOD)) % MOD);
			return;
		}
		
		long res1 = (pow(A, B) - 1) % MOD;
		long res2 = pow((A - 1), (MOD - 2)) % MOD;
		
		System.out.println((res1 * res2) % MOD);
	}
	
	static long pow(long x, long y) {
		if(y == 0) {
			return 1;
		}
		
		long res = pow(x, y / 2);
		
		if(y % 2 == 0) {
			return ((res % MOD) * (res % MOD)) % MOD;
		} else {
			return ((((res % MOD) * (res % MOD)) % MOD) * x % MOD) % MOD;
		}
	}
	
}