import java.util.*;
import java.io.*;

public class Main {
	
	static int[] ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[N + 1][2];
			
			if(N == 0) {
				System.out.println("1 0");
				continue;
			}
			
			dp[0] = new int[]{1, 0};
			dp[1] = new int[] {0, 1};
			
			for(int i = 2; i <= N; i++) {
				dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
				dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
			}
			
			System.out.println(dp[N][0] + " " + dp[N][1]);
		}
	}
}
