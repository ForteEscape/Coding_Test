import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] weight;
	static boolean[] visited;
	static List<List<Integer>> perm;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		weight = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			dfs(i, 0, 0, i);
		}
		
		System.out.println(ans);
	}
	
	public static void dfs(int startNode, int currentWeight, int cnt, int originalNode) {
		visited[startNode] = true;
		
		if(currentWeight > ans) {
			return;
		}
		
		
		if(cnt == N - 1) {
			//System.out.println(startNode + " " + currentWeight + " " + cnt + " " + originalNode);
			if(weight[startNode][originalNode] == 0) {
				return;
			}
			
			int result = currentWeight + weight[startNode][originalNode];
			
			ans = Math.min(ans, result);
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && weight[startNode][i] != 0) {
				visited[i] = true;
				dfs(i, currentWeight + weight[startNode][i], cnt + 1, originalNode);
				visited[i] = false;
			}
		}
	}
}