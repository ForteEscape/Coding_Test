import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge {
		int to;
		long weight;
		
		Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int V, E;
	static long[] dist;
	static long[] dist2;
	static List[] graph;
	static final long INF = 50000000000000L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[V + 1];
		dist = new long[V + 1];
		dist2 = new long[V + 1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<Edge>();
			dist[i] = INF;
			dist2[i] = INF;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start, dist);
		dijkstra(end, dist2);
		
		int P = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		long hubDist = Long.MAX_VALUE;
		for(int i = 0; i < P; i++) {
			int element = Integer.parseInt(st.nextToken());
			
			if(dist[element] == INF || dist2[element] == INF) {
				continue;
			}
			 
			hubDist = Math.min(hubDist, dist[element] + dist2[element]);
		}
		
		if(hubDist == Long.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(hubDist);
		}
	}
	
	public static void dijkstra(int startNode, long[] dist) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) ->  {
			if(e1.weight - e2.weight > 0) {
				return 1;
			} else {
				return -1;
			}
		});
		
		dist[startNode] = 0;
		pq.offer(new Edge(startNode, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.to] > cur.weight) {
				continue;
			}
			
			if(dist[cur.to] < cur.weight) { 
				continue;
			}
			
			for(int i = 0; i < graph[cur.to].size(); i++) {
				Edge next = (Edge) graph[cur.to].get(i);
				
				if(dist[next.to] > cur.weight + next.weight) {
					dist[next.to] = cur.weight + next.weight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}