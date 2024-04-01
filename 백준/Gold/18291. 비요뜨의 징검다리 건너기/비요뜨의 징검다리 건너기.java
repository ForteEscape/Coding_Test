import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	private static int MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			System.out.println(getPower(2, Math.max(N - 2, 0)) % MOD);
		}
	}
	
	static long getPower(int x, int y) {
		if(y == 0) {
			return 1;
		}
		
		long res = getPower(x, y / 2);
		
		if(y % 2 == 0) {
			return ((res % MOD) * (res % MOD)) % MOD;
		} else {
			return (((res % MOD) * (res % MOD)) % MOD * (x % MOD)) % MOD;
		}
	}
}