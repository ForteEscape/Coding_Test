import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int idx;
		ArrayList<Integer> parents;
		int strahler;
		
		Edge(int idx) {
			this.idx = idx;
			this.parents = new ArrayList<>();
		}
	}
	
	static int tc, V, E;
	static List<Integer>[] graph;
	static int[] indegree;
	static Edge[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			tc = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			indegree = new int[V + 1];
			graph = new List[V + 1];
			edges = new Edge[V + 1];
			
			for(int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
				edges[i] = new Edge(i);
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				edges[to].parents.add(from);
				
				indegree[to]++;
			}
			
			Deque<Integer> queue = new ArrayDeque<>();
			for(int i = 1; i <= V; i++) {
				if(indegree[i] == 0) {
					queue.addLast(i);
					edges[i].strahler = 1;
				}
			}
			
			while(!queue.isEmpty()) {
				int cur = queue.pollFirst();
				
				for(int adjNode : graph[cur]) {
					indegree[adjNode]--;
					
					if(indegree[adjNode] == 0) {
						Edge edge = edges[adjNode];
						TreeMap<Integer, Integer> map = new TreeMap<>();
						
						for(int parent : edge.parents) {
							map.put(edges[parent].strahler, map.getOrDefault(edges[parent].strahler, 0) + 1);
						}
						
						edge.strahler = map.get(map.lastKey()) >= 2 ? map.lastKey() + 1 : map.lastKey();
						queue.addLast(adjNode);
					}
				}
			}
			
			int ans = -1;
			for(int i = 1; i <= V; i++) {
				ans = Math.max(ans, edges[i].strahler);
			}
			
			sb.append(tc).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
}