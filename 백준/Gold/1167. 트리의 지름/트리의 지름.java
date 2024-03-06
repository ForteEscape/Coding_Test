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
	
	static int N;
	static List<Edge>[] graph;
	static boolean[] visited;
	static int maxWeight;
	static int maxWeightNode;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int parent = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int V = Integer.parseInt(st.nextToken());
				
				if(V != -1) {
					int weight = Integer.parseInt(st.nextToken());
					
					graph[parent].add(new Edge(V, weight));
				}
			}
		}
		
		visited = new boolean[N + 1];
		maxWeight = Integer.MIN_VALUE;
		maxWeightNode = -1;
		
		dfs(1, 0);
		
		int startNode = maxWeightNode;
		maxWeightNode = -1;
		maxWeight = -1;
		
		visited = new boolean[N + 1];
		dfs(startNode, 0);
		System.out.println(maxWeight);
	}
	
	public static void dfs(int startNode, int currentWeight) {
		visited[startNode] = true;
		
		if(maxWeight < currentWeight) {
			maxWeight = currentWeight;
			maxWeightNode = startNode;
		}
		
		for(Edge next : graph[startNode]) {
			if(!visited[next.to]) {
				dfs(next.to, currentWeight + next.weight);
			}
		}
	}
}