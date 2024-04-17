import java.util.*;
import java.io.*;

public class Main {
	
	static int N, K;
	static int[] data;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		data = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = Integer.MIN_VALUE;
		solve(0, 0, 1);
		
		System.out.println(ans);
	}
	
	static void solve(int cur, int time, int size) {
		if(cur >= N || time == K) {
			ans = Math.max(ans, size);
			return;
		}
		
		solve(cur + 1, time + 1, size + data[cur + 1]);
		
		if(cur + 2 <= N) {
			solve(cur + 2, time + 1, (size / 2) + data[cur + 2]);
		}
	}
}