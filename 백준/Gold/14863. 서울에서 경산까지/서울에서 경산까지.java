import java.util.*;
import java.io.*;

class Main {

	static final int INF = Integer.MIN_VALUE;
	static int[][] dp;
	static int[] v1, c1, v2, c2;
	static int N, K;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][K + 1];
		c1 = new int[N + 1];
		v1 = new int[N + 1];
		c2 = new int[N + 1];
		v2 = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			c1[i] = Integer.parseInt(st.nextToken());
			v1[i] = Integer.parseInt(st.nextToken());
			c2[i] = Integer.parseInt(st.nextToken());
			v2[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(solve(N, K));
	}

	static int solve(int y, int x) {
		if(y == 0) {
			return 0;
		}
		
		if(dp[y][x] != -1) {
			return dp[y][x];
		}
		
		dp[y][x] = INF;
		if(x - c1[y] >= 0) {
			dp[y][x] = Math.max(dp[y][x], solve(y - 1, x - c1[y]) + v1[y]);
		}
		
		if(x - c2[y] >= 0) {
			dp[y][x] = Math.max(dp[y][x], solve(y - 1, x - c2[y]) + v2[y]);
		}
		
		return dp[y][x];
	}
}