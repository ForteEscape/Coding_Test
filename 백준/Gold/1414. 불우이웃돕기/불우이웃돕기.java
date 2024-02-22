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
	
	static int[] size;
	static int[] parent;
	static Map<Character, Integer> weightMap;
	static int V;
	static List<Edge> edges;
	
	static {
		weightMap = new HashMap<>();
		
		for(char i = 'a'; i <= 'z'; i++) {
			weightMap.put(i, i - 'a' + 1);
		}
		
		for(char i = 'A'; i <= 'Z'; i++) {
			weightMap.put(i, (i - 'A') + 27);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		
		size = new int[V + 1];
		parent = new int[V + 1];
		edges = new ArrayList<>();
		
		for(int i = 1; i <= V; i++) {
			size[i] = 1;
			parent[i] = i;
		}
		
		int ans = 0;
		for(int i = 1; i <= V; i++) {
			String line = br.readLine();
			for(int j = 0; j < V; j++) {
				if(line.charAt(j) == '0') {
					continue;
				}
				
				ans += weightMap.get(line.charAt(j));
				
				if(i != j + 1) {
					edges.add(new Edge(i, j + 1, weightMap.get(line.charAt(j))));
				}
			}
		}
		
		Collections.sort(edges, (e1, e2) -> e1.weight - e2.weight);
		
		int cnt = 0;
		for(int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			
			if(find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				ans -= edge.weight;
				cnt++;
			}
			
			if(cnt == V - 1) {
				break;
			}
		}
		
		if(cnt != V - 1) {
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