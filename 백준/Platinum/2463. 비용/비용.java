import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	}
	
	static int[] parent;
	static int[] size;
	static Edge[] edges;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		size = new int[V + 1];
		edges = new Edge[E];
		
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		long sum = 0;
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			sum += weight;
			
			edges[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edges, Comparator.comparingInt(e -> -e.weight));
		
		long ans = 0L;
		for(Edge edge : edges) {
			int pa = find(edge.from);
			int pb = find(edge.to);
			
			if(pa != pb) {
				ans += sum * size[pa] * size[pb];
				union(edge.from, edge.to);
			}
			
			sum -= edge.weight;
		}
		
		System.out.println(ans % 1_000_000_000L);
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(size[pa] <= size[pb]) {
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