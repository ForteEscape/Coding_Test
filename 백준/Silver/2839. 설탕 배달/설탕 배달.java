import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		if(N == 4) {
			System.out.println(-1);
			return;
		}
		
		if(N == 3) {
			System.out.println(1);
			return;
		}
		
		Arrays.fill(dp, 9999);
		
		dp[3] = 1;
		dp[5] = 1;
		
		for(int i = 6; i <= N; i++) {
			dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
		}
		
		if(dp[N] >= 9999) {
			System.out.println(-1);
			return;
		}
		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
	}
}