import java.util.*;
import java.io.*;

class Main {
	
	static int[] cows;
	static int C, N;
	static int ans;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		cows = new int[N];
		
		for(int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(br.readLine());
		}
		
		ans = 0;
		solve(0, 0);
		
		System.out.println(ans);
	}
	
	static void solve(int cur, int curWeight) {
		if(cur == N) {
			ans = Math.max(ans, curWeight);
			return;
		}
		
		if(curWeight + cows[cur] <= C) {
			solve(cur + 1, curWeight + cows[cur]);
		}
		
		solve(cur + 1, curWeight);
	}
}