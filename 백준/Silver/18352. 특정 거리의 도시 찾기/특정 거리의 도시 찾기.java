import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int V, E, K, X;
	static List<Edge>[] graph;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dist = new int[V + 1];
		graph = new List[V + 1];
		
		for(int i = 1; i <= V; i++) {
			dist[i] = INF;
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Edge(to, 1));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
		pq.offer(new Edge(X, 0));
		dist[X] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
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
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(dist[i] == K) {
				sb.append(i).append("\n");
			}
		}
		
		System.out.print(sb.length() == 0 ? "-1\n" : sb);
	}
 }