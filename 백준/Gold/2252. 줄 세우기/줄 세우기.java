import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
//8 directions
public class Main {
	
	static int[] indegree;
	static List<Integer>[] graph;
	static int V, E;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[V + 1];
		indegree = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i< E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			indegree[to]++;
		}
		
		Deque<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		 
		for(int i = 1; i <= V; i++) {
			if(indegree[i] == 0) {
				sb.append(i).append(" ");
				queue.addLast(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			
			for(int adjNode : graph[cur]) {
				indegree[adjNode]--;
				
				if(indegree[adjNode] == 0) {
					sb.append(adjNode).append(" ");
					queue.addLast(adjNode);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}