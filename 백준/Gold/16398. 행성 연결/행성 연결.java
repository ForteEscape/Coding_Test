import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


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
	
	static int N;
	static StringTokenizer st;
	static int[] size;
	static int[] parent;
	static List<Edge> edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		edges = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				
				if(i == j) continue;
				edges.add(new Edge(i, j, weight));
			}
		}
		
		size = new int[N + 1];
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		edges.sort((e1, e2) -> e1.weight - e2.weight);
		
		long ans = 0;
		int cnt = 0;
		for(Edge edge : edges) {
			if(find(edge.from) != find(edge.to)) {
				ans += edge.weight;
				union(edge.from, edge.to);
				cnt++;
			}
			
			if(cnt == N - 1) {
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