import java.util.*;
import java.io.*;

class Main {
	
	static int N, C;
	static List<Integer>[] tree;
	static int[] dist;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		tree = new List[N + 1];
		
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			tree[v].add(left);
			tree[v].add(right);
		}
		
		dist = new int[N + 1];
		dist[1] = 1;
		bfs(1);
		
		for(int i = 1; i <= N; i++) {
			System.out.println(dist[i]);
		}
	}
	
	static void bfs(int start) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.addLast(start);
		
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			
			for(int adjNode : tree[cur]) {
				dist[adjNode] = dist[cur] + 1;
				queue.addLast(adjNode);
			}
		}
	}
}