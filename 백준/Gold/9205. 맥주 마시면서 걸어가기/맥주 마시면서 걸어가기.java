import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static class Pair {
		int y;
		int x;
		
		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static List<Edge>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			Pair[] locations = new Pair[N + 3];
			for(int i = 1; i <= N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				locations[i] = new Pair(y, x);
			}
			
			graph = new List[N + 3];
			
			for(int i = 1; i <= N + 2; i++) {
				graph[i] = new ArrayList<>();
			}
			
			
			for(int i = 1; i <= N + 1; i++) {
				for(int j = i + 1; j <= N + 2; j++) {
					int dist = getDist(locations[i], locations[j]);
					graph[i].add(new Edge(j, dist));
					graph[j].add(new Edge(i, dist));
				}
			}
			
			boolean[] visited = new boolean[N + 3];
			Deque<Edge> queue = new ArrayDeque<>();
			
			queue.addLast(new Edge(1, 0));
			visited[1] = true;
			
			while(!queue.isEmpty()) {
				Edge cur = queue.pollFirst();
				
				for(Edge next: graph[cur.to]) {
					if(!visited[next.to] && Math.abs(next.weight - cur.weight) <= 1000) {
						visited[next.to] = true;
						queue.addLast(new Edge(next.to, 0));
					}
				}
			}
			
			sb.append(visited[N + 2] ? "happy" : "sad").append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int getDist(Pair p1, Pair p2) {
		return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
	}
}