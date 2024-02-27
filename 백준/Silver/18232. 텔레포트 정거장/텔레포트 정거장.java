import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int x;
		int cnt;
		
		Location(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static int[] dx = {1, -1};
	static List<Integer>[] teleport;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		teleport = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			teleport[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			teleport[from].add(to);
			teleport[to].add(from);
		}
		
		boolean[] visited = new boolean[N + 1];
		Deque<Location> queue = new ArrayDeque<>();
		
		visited[S] = true;
		queue.addLast(new Location(S, 0));
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			if(cur.x == E) {
				System.out.println(cur.cnt);
				break;
			}
			
			for(int i = 0; i < 2; i++) {
				int nx = cur.x + dx[i];
				
				if(isUnreachable(nx) || visited[nx]) {
					continue;
				}
				
				visited[nx] = true;
				queue.addLast(new Location(nx, cur.cnt + 1));
			}
			
			for(int element : teleport[cur.x]) {
				int nx = element;
				
				if(visited[element]) {
					continue;
				}
				
				visited[element] = true;
				queue.addLast(new Location(nx, cur.cnt + 1));
			}
		}
		
	}
	
	public static boolean isUnreachable(int x) {
		return x < 1 || x > N;
	}
	
}