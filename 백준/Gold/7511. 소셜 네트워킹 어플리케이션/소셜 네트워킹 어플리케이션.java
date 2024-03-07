import java.util.*;
import java.io.*;

public class Main {
	
	static int T, N, K, Q;
	static int[] parent;
	static int[] size;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			
			parent = new int[N + 1];
			size = new int[N + 1];
			
			for(int i = 0; i <= N; i++) {
				parent[i] = i;
				size[i] = 1;
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			Q = Integer.parseInt(br.readLine());
			
			StringBuilder sb = new StringBuilder();
			sb.append("Scenario ").append(tc).append(":\n");
			for(int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				sb.append(find(a) == find(b) ? 1 : 0).append("\n");
			}
			System.out.println(sb);
		}
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(size[pa] >= size[pb]) {
			parent[pb] = pa;
			size[pa] += size[pb];
		} else {
			parent[pa] = pb;
			size[pb] += size[pa];
		}
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return parent[x];
		}
		
		return parent[x] = find(parent[x]);
	}
}