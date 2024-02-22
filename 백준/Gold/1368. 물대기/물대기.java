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
	
	static int[] parent;
	static int[] size;
	static int V;
	static List<Edge> edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V = Integer.parseInt(br.readLine());
		
		parent = new int[V + 1];
		size = new int[V + 1];
		edges = new ArrayList<>();
		
		for(int i = 0; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		for(int i = 1; i <= V; i++) {
			int weight = Integer.parseInt(br.readLine());
			edges.add(new Edge(0, i, weight));
		}
		
		for(int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= V; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if(j <= i) continue;
				
				edges.add(new Edge(i, j, weight));
			}
		}
		
		Collections.sort(edges, (e1, e2) -> e1.weight - e2.weight);
		long ans = 0L;
		int cnt = 0;
		
		for(int i = 0; i < edges.size(); i++) {
			if(find(edges.get(i).from) != find(edges.get(i).to)) {
				union(edges.get(i).from, edges.get(i).to);
				ans += edges.get(i).weight;
				cnt++;
			}
			
			if(cnt == V) {
				break;
			}
		}
		
		System.out.println(ans);
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