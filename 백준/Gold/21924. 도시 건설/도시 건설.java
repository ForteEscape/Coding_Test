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
	}
	
	static int V, E;
	static int[] parent;
	static int[] size;
	static Edge[] edges;
	
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
		
		long ans = 0;
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			ans += weight;
			
			edges[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edges, (e1, e2) -> e1.weight - e2.weight);
		
		int cnt = 0;
		for(int i = 0; i < E; i++) {
			if(find(edges[i].from) != find(edges[i].to)) {
				union(edges[i].from, edges[i].to);
				ans -= edges[i].weight;
				cnt++;
			}
			
			if(cnt == V - 1) {
				break;
			}
		}
		
		System.out.println(cnt == V - 1 ? ans : -1);
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