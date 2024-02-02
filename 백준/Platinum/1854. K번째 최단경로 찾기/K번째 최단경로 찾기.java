import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int V;
	static int E;
	static int K;
	static List<List<Edge>> graph;
	static PriorityQueue<Integer>[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		dist = new PriorityQueue[V + 1];
		
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
			dist[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, weight));
		}
		
		solve(1);
		
		for(int i = 1; i<= V; i++) {
			if(dist[i].size() == K) {
				System.out.println(dist[i].peek());
			} else {
				System.out.println(-1);
			}
		}
	}
	
	public static void solve(int startNode) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
		pq.offer(new Edge(startNode, 0));
		dist[startNode].offer(0);
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			for(int i = 0; i < graph.get(cur.to).size(); i++) {
				Edge adjEdge = graph.get(cur.to).get(i);
				
				int nextWeight = cur.weight + adjEdge.weight;
				if(dist[adjEdge.to].size() < K) {
					dist[adjEdge.to].offer(nextWeight);
					pq.offer(new Edge(adjEdge.to, nextWeight));
				} else {
					if(dist[adjEdge.to].peek() > nextWeight) {
						dist[adjEdge.to].poll();
						dist[adjEdge.to].offer(nextWeight);
						
						pq.offer(new Edge(adjEdge.to, nextWeight));
					}
				}
			}
		}
	}
}