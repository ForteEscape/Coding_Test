import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge {
		int idx;
		int cost;
		
		Edge(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	static List<List<Edge>> graph = new ArrayList<>();
	static int V;
	static int M;
	static StringTokenizer st;
	static BitSet visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < V - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, cost));
			graph.get(to).add(new Edge(from, cost));
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			System.out.println(bfs(start, end));
		}
	}
	
	public static int bfs(int start, int end) {
		Deque<Edge> queue = new ArrayDeque<>();
		queue.addLast(new Edge(start, 0));
		visited = new BitSet(1001);
		visited.set(start);
		
		while(!queue.isEmpty()) {
			Edge cur = queue.pollFirst();
			
			if(cur.idx == end) {
				return cur.cost;
			}
			
			for(int i = 0; i < graph.get(cur.idx).size(); i++) {
				Edge adjNode = graph.get(cur.idx).get(i);
				
				if(visited.get(adjNode.idx)) {
					continue;
				}
				
				visited.set(adjNode.idx);
				queue.addLast(new Edge(adjNode.idx, cur.cost + adjNode.cost));
			}
		}
		
		return 0;
	}

}