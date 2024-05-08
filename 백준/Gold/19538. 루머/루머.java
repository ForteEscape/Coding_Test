import java.util.*;
import java.io.*;

public class Main {
	
	static List<Integer>[] graph;;
	static int[] time;
	static int[] adj;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		graph = new List[N + 1];
		time = new int[N + 1];
		adj = new int[N + 1];
		
		Arrays.fill(time, -1);
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int adjNode = 0;
			while((adjNode = Integer.parseInt(st.nextToken())) != 0) {
				graph[i].add(adjNode);
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		Deque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 0; i < M; i++) {
			int rumor = Integer.parseInt(st.nextToken());
			time[rumor] = 0;
			queue.addLast(rumor);
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			
			for(int adjNode : graph[cur]) {
				adj[adjNode]++;
				
				if(time[adjNode] == -1 && adj[adjNode] >= (graph[adjNode].size() + 1) / 2) {
					time[adjNode] = time[cur] + 1;
					queue.addLast(adjNode);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(time[i]).append(" ");
		}
		
		System.out.println(sb);
		
	}
}