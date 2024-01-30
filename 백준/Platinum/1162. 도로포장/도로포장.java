import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int to;
		long cost;
		int count;
		
		Edge(int to, long cost, int count){
			this.to = to;
			this.cost = cost;
			this.count = count;
		}
	}
	
	public static List<List<Edge>> graph;
	public static long[][] dist;
	public static final long INF = Long.MAX_VALUE;
	public static int V;
	public static int E;
	public static int K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		dist = new long[V + 1][K + 1];
		
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
			
			for(int j = 0; j <= K; j++) {
				dist[i][j] = INF;
			}
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, cost, 0));
			graph.get(to).add(new Edge(from, cost, 0));
		}
		
		dijkstra();
		
		long ans = Long.MAX_VALUE;
		
		for(long element : dist[V]) {
			ans = Math.min(ans, element);
		}
		
		System.out.println(ans);
	}
	
	public static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> (int)(e1.cost - e2.cost));
		pq.add(new Edge(1, 0, 0));
		
		dist[1][0] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(cur.cost > dist[cur.to][cur.count]) {
				continue;
			}
			
			for(Edge next : graph.get(cur.to)) {
				long nextCost = cur.cost + next.cost;
				
				if(nextCost < dist[next.to][cur.count]) {
					dist[next.to][cur.count] = nextCost;
					pq.offer(new Edge(next.to, dist[next.to][cur.count], cur.count));
				}
				
				// K회 이하로 포장된 상태이고 현재 코스트(포장하면 다음 코스트는 0이므로) 가 해당 도시로 이동하는 최소 이동값보다 더 작다면
				if(cur.count < K && cur.cost < dist[next.to][cur.count + 1]) {
					dist[next.to][cur.count + 1] = cur.cost;
					pq.offer(new Edge(next.to, dist[next.to][cur.count + 1], cur.count + 1));
				}
			}
		}
	}
}
