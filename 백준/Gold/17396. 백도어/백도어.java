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
	static BitSet isVisible;
	static final long INF = Long.MAX_VALUE;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new long[V];
		graph = new List[V];
		isVisible = new BitSet(100000);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < V; i++) {
			int flag = Integer.parseInt(st.nextToken());
			if(flag == 1 && i != V - 1) {
				isVisible.set(i);
			}
			
			dist[i] = INF;
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}
		
		if(isVisible.get(0)) {
			System.out.println(-1);
			return;
		}
		
		dijkstra(0);
		
		if(dist[V - 1] == INF) {
			System.out.println(-1);
		} else {
			System.out.println(dist[V - 1]);
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));
		dist[start] = 0;
		
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.to] > cur.weight) {
				continue;
			}
			
			if(dist[cur.to] < cur.weight) {
				continue;
			}
			
			for(Edge next : graph[cur.to]) {
				if(!isVisible.get(next.to) && dist[next.to] > next.weight + cur.weight) {
					dist[next.to] = next.weight + cur.weight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}