import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int to;
		int cnt;
		
		Edge(int to, int cnt) {
			this.to = to;
			this.cnt = cnt;
		}
	}
	
	static int V, E;
	static List<Integer>[] graph;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[V + 1];
		dist = new int[V + 1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
		}
		
		for(int i = 1; i <= V; i++) {
			if(!dijkstra(i)) {
				System.out.println("Big World!");
				return;
			}
		}
		
		System.out.println("Small World!");
	}
	
	static boolean dijkstra(int start) {
		for(int i = 1; i <= V; i++) {
			dist[i] = INF;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cnt));
		pq.offer(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.to] < cur.cnt) {
				continue;
			}
			
			for(int adjNode : graph[cur.to]) {
				if(dist[adjNode] > dist[cur.to] + 1) {
					dist[adjNode] = dist[cur.to] + 1;
					pq.offer(new Edge(adjNode, dist[adjNode]));
				}
			}
		}
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] > 6) {
				return false;
			}
		}
		
		return true;
	}
	
}