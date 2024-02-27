import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[N + 2];
		dp[1] = 4L;
		dp[2] = 6L;
		
		for(int i = 3; i <= N; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		
		System.out.println(dp[N]);
	}
	
}