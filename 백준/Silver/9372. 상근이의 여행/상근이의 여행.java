import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int from;
		int to;
		
		Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	static Edge[] edges;
	static int[] parents;
	static int[] size;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			size = new int[V + 1];
			parents = new int[V + 1];
			edges = new Edge[E];
			
			for(int i = 1; i <= V; i++) {
				parents[i] = i;
				size[i] = 1;
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(from, to);
			}
			
			int ans = 0;
			for(int i = 0; i < E; i++) {
				Edge edge = edges[i];
				
				if(find(edge.from) != find(edge.to)) {
					union(edge.from, edge.to);
					ans++;
				}
				
				if(size[find(1)] == V) {
					break;
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(size[pa] <= size[pb]) {
			parents[pb] = pa;
			size[pa] += size[pb];
		} else {
			parents[pa] = pb;
			size[pb] += size[pa];
		}
	}
	
	static int find(int x) {
		if(x == parents[x]) {
			return parents[x];
		}
		
		return parents[x] = find(parents[x]);
	}
}