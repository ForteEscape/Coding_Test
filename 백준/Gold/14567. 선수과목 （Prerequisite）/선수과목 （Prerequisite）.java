import java.util.*;
import java.io.*;

class Main {
	
	static class Pair {
		int idx;
		int data;
		
		Pair(int idx, int data) {
			this.idx = idx;
			this.data = data;
		}
	}
	
	static int V, E;
	static int[] indegree;
	static int[] ans;
	static List<Integer>[] graph;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		indegree = new int[V + 1];
		graph = new List[V + 1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			
			indegree[to]++;
		}
		
		Deque<Pair> queue = new ArrayDeque<>();
		ans = new int[V + 1];
		
		for(int i = 1; i <= V; i++) {
			if(indegree[i] == 0) {
				ans[i] = 1;
				queue.addLast(new Pair(i, 1));
			}
		}
		
		while(!queue.isEmpty()) {
			Pair cur = queue.pollFirst();
			
			ans[cur.idx] = cur.data;
			
			for(int adjNode: graph[cur.idx]) {
				indegree[adjNode]--;
				
				if(indegree[adjNode] == 0) {
					queue.addLast(new Pair(adjNode, cur.data + 1));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
}