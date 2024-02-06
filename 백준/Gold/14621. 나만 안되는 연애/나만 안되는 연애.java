import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
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
	
	static int V, E;
	static StringTokenizer st;
	static BitSet type;
	static int[] parent;
	static int[] size;
	static Edge[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		type = new BitSet(1001);
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= V; i++) {
			char typeData = st.nextToken().charAt(0);
			
			if(typeData == 'M') {
				type.set(i);
			}
		}
		
		edges = new Edge[E];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(from, to, weight);
		}
		
		parent = new int[V + 1];
		size = new int[V + 1];
		
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		Arrays.sort(edges, (e1, e2) -> e1.weight - e2.weight);
		int cnt = 0;
		int ans = 0;
		for(Edge edge : edges) {
			if(type.get(edge.from) != type.get(edge.to)) {
				if(find(edge.from) != find(edge.to)) {
					union(edge.from, edge.to);
					ans += edge.weight;
					cnt++;
				}
				
				if(cnt == V - 1) {
					break;
				}
			}
		}
		
		if(cnt < V - 1) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
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