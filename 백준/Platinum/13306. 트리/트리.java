import java.util.*;
import java.io.*;

public class Main {

	static int V, Q;
	static int[] parent, size, graph;
	static int[][] query;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		size = new int[V + 1];
		graph = new int[V + 1];
		query = new int[Q + V - 1][];
		
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		for(int i = 2; i <= V; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < Q + V - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int opt = Integer.parseInt(st.nextToken());
			
			if(opt == 0) {
				int node = Integer.parseInt(st.nextToken());
				query[i] = new int[]{opt, node};
			} else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				query[i] = new int[]{opt, start, end};
			}
		}
		
		Deque<String> stack = new ArrayDeque<>();
		
		for(int i = query.length - 1; i >= 0; i--) {
			int[] queryRow = query[i];
			
			if(queryRow[0] == 0) {
				union(queryRow[1], graph[queryRow[1]]);
			} else {
				if(find(queryRow[1]) == find(queryRow[2])) {
					stack.addLast("YES");
				} else {
					stack.addLast("NO");
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pollLast()).append("\n");
		}
		System.out.print(sb);
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
		if(x == parent[x]) {
			return parent[x];
		}
		
		return parent[x] = find(parent[x]);
	}
}