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

	static long[] distMc;
	static long[] distSt;
	static List<Edge>[] graph;
	static int V, E;
	static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		distMc = new long[V + 1];
		distSt = new long[V + 1];
		graph = new List[V + 1];
		
		for(int i = 1; i <= V; i ++) {
			distMc[i] = INF;
			distSt[i] = INF;
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
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int MDist = Integer.parseInt(st.nextToken());
		
		Set<Integer> macSet = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int macLocation = Integer.parseInt(st.nextToken());
			macSet.add(macLocation);
			distMc[macLocation] = 0;
		}
		
		dijkstra(macSet, distMc);
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int SDist = Integer.parseInt(st.nextToken());
		
		Set<Integer> starSet = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < S; i++) {
			int starLocation = Integer.parseInt(st.nextToken());
			starSet.add(starLocation);
			distSt[starLocation] = 0;
		}
		
		dijkstra(starSet, distSt);
		
		long ans = Long.MAX_VALUE;
		for(int i = 1; i <= V; i++) {
			if(macSet.contains(i) || starSet.contains(i)) {
				continue;
			}
			
			if(distMc[i] != INF && distSt[i] != INF && distMc[i] <= MDist && distSt[i] <= SDist) {
				ans = Math.min(ans, distMc[i] + distSt[i]);
			}
		}
		
		if(ans == Long.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
	
	public static void dijkstra(Set<Integer> startSet, long[] dist) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));
		for(int element : startSet) {
			pq.offer(new Edge(element, 0));
		}
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.to] > cur.weight) {
				continue;
			}
			
			if(dist[cur.to] < cur.weight) {
				continue;
			}
			
			for(Edge next : graph[cur.to]) {
				if(dist[next.to] > next.weight + cur.weight) {
					dist[next.to] = next.weight + cur.weight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}