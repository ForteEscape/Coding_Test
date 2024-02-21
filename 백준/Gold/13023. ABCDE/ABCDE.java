import java.io.*;
import java.util.*;

public class Main {
	
	static List<Integer>[] graph;
	static int V, E;
	static boolean[] visited;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[V];
		for(int i = 0; i < V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
		}
		
		for(int i = 0; i < V; i++) {
			visited = new boolean[V];
			flag = false;
			dfs(i, 1);
			
			if(flag) {
				System.out.println(1);
				return;
			}
		}
		
		System.out.println(0);
	}
	
	public static void dfs(int startNode, int depth) {
		if(depth == 5) {
			flag = true;
			return;
		}
		
		visited[startNode] = true;
		
		for(int adjNode : graph[startNode]) {
			if(!visited[adjNode]) {
				visited[adjNode] = true;
				dfs(adjNode, depth + 1);
				visited[adjNode] = false;
			}
		}
	}
}