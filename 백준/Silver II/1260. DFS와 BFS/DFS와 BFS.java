import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static StringTokenizer st;
	static List<Integer>[] graph;
	static int V, E, startNode;
	static StringBuilder sb;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(st.nextToken());
		
		graph = new List[V + 1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
		}
		
		for(int i = 1; i <= V; i++) {
			Collections.sort(graph[i]);
		}
		
		sb = new StringBuilder();
		visited = new boolean[V + 1];
		dfs(startNode);
		System.out.println(sb);
		
		sb = new StringBuilder();
		visited=  new boolean[V + 1];
		bfs();
		System.out.println(sb);
	}
	public static void dfs(int startNode) {
		sb.append(startNode).append(" ");
		visited[startNode] = true;
		
		for(int i = 0; i < graph[startNode].size(); i++) {
			int adjNode = graph[startNode].get(i);
			
			if(!visited[adjNode]) {
				dfs(adjNode);
			}
		}
	}
	
	public static void bfs() {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.addLast(startNode);
		visited[startNode] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			sb.append(cur).append(" ");
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int adjNode = graph[cur].get(i);
				
				if(!visited[adjNode]) {
					visited[adjNode] = true;
					queue.addLast(adjNode);
				}
			}
		}
	}
}