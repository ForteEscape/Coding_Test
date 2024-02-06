import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Edge {
		int from;
		int to;
		int number;
		int weight;

		Edge(int from, int to, int number, int weight) {
			this.from = from;
			this.to = to;
			this.number = number;
			this.weight = weight;
		}
	}

	static int V, E;
	static int[] parent;
	static int[] size;
	static Edge[] edges;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		edges = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(from, to, num, weight);
		}

		Arrays.sort(edges, (e1, e2) -> e1.number - e2.number == 0 ? (e1.weight - e2.weight) : (e1.number - e2.number));
		parent = new int[V + 1];
		size = new int[V + 1];
		
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		long ans = 0;
		int cnt = 0;
		for(Edge edge : edges) {
			if(find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				ans += edge.weight;
				sb.append(edge.number);
				cnt++;
			}
			
			if(cnt == V - 1) {
				break;
			}
		}
		
		Set<Integer> disjointSet = new HashSet<>();
		for(int i = 1; i <= V; i++) {
			disjointSet.add(find(i));
		}
		
		if(disjointSet.size() != 1) {
			System.out.println(-1);
		} else {
			System.out.println(sb.toString() + " " + ans);
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