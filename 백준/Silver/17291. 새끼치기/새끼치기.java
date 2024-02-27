import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[N + 2];
		dp[1] = 1L;
		dp[2] = 2L;
		
		for(int i = 3; i <= N; i++) {
			if(i % 2 != 0) {
				dp[i] = dp[i - 1] * 2;
			} else {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}
		}
		
		System.out.println(dp[N]);
	}
	
}