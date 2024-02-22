import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge {
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
		
	}
	
	static int[] parent;
	static int[] size;
	static List<Edge> edges;
	static int V, E, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		edges = new LinkedList<>();
		parent = new int[V + 1];
		size = new int[V + 1];
		
		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(from, to, i));
		}
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i = 0; i < K; i++) {
			if(flag) {
				sb.append(0).append(" ");
				continue;
			}
			
			int ans = kruskal();
			
			if(ans == 0) {
				flag = true;
				sb.append(0).append(" ");
			} else {
				sb.append(ans).append(" ");
			}
			
			edges.remove(0);
		}
		
		System.out.println(sb);
	}
	
	public static int kruskal() {
		int cnt = 0;
		int ans = 0;
		
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		for(Edge edge : edges) {
			if(find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				cnt++;
				ans += edge.weight;
			}
			
			if(cnt == V - 1) {
				break;
			}
		}
		
		return cnt == V - 1 ? ans : 0;
	}
	
	public static void union(int a, int b) {
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
	
	public static int find(int x) {
		if(x == parent[x]) {
			return parent[x];
		}
		
		return parent[x] = find(parent[x]);
	}
}