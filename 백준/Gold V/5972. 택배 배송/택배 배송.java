import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int V, E;
	static int[] cost;
	static final int INF = Integer.MAX_VALUE;
	static List<Edge>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[V + 1];
		cost = new int[V + 1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<Edge>();
			cost[i] = INF;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}
		
		dijkstra(1);
		
		System.out.println(cost[V]);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> (e1.weight - e2.weight));
		pq.offer(new Edge(start, 0));
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(cost[cur.to] > cur.weight) {
				continue;
			}
			
			if(cost[cur.to] < cur.weight) {
				continue;
			}
			
			for(int i = 0; i < graph[cur.to].size(); i++) {
				Edge next = graph[cur.to].get(i);
				
				if(cost[next.to] > cur.weight + next.weight) {
					cost[next.to] = cur.weight + next.weight;
					pq.offer(new Edge(next.to, cost[next.to]));
				}
			}
		}
	}
}