import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static class Edge {
		int to;
		long weight;
		
		Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static long[] dist;
	static List<Edge>[] graph;
	static final long INF = Long.MAX_VALUE;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new long[V + 1];
		graph = new List[V + 1];
		for(int i = 1; i <= V; i++) {
			dist[i] = INF;
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		System.out.println(dist[end]);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));
		pq.offer(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.to] > cur.weight) {
				continue;
			}
			
			if(dist[cur.to] < cur.weight) {
				continue;
			}
			
			for(Edge next : graph[cur.to]) {
				if(dist[next.to] > cur.weight + next.weight) {
					dist[next.to] = cur.weight + next.weight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}