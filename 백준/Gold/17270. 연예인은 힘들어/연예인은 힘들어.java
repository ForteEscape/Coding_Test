import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	static List[] graph;
	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[V +  1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int[] dist = new int[V + 1];
		int[] dist2 = new int[V + 1];
		
		for(int i = 1; i <= V; i++) {
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
		int jihyon = Integer.parseInt(st.nextToken());
		int sungha = Integer.parseInt(st.nextToken());
		
		dijkstra(jihyon, dist);
		dijkstra(sungha, dist2);
		
		int distMin = Integer.MAX_VALUE;
		for(int i = 1; i <= V; i++) {
			if(i == jihyon || i == sungha) {
				continue;
			}
			distMin = Math.min(distMin, (dist[i] + dist2[i]));
		}
		
		int ans = -1;
		int minDistJihyon = Integer.MAX_VALUE;
		for(int i = 1; i <= V; i++) {
			if(i == jihyon || i == sungha) {
				continue;
			}
			
			if(dist[i] + dist2[i] == distMin && dist[i] <= dist2[i]) {
				if(dist[i] < minDistJihyon) {
					minDistJihyon = dist[i];
					ans = i;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	public static void dijkstra(int start, int[] dist) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
		dist[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.to] > cur.weight) {
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