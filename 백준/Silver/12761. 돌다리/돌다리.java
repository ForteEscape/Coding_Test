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
	static int A, B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] dx = {1, -1, A, -A, B, -B};
		
		Deque<Location> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		
		queue.addLast(new Location(N, 0));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			if(cur.x == M) {
				System.out.println(cur.cnt);
				break;
			}
			
			for(int i = 0; i < 8; i++) {
				int nx = -1;
				if(i >= 6) {
					if(i == 6) {
						nx = cur.x * A;
					} else {
						nx = cur.x * B;
					}
				} else {
					nx = cur.x + dx[i];
				}
				
				if(isUnreachable(nx) || visited[nx]) {
					continue;
				}
				
				visited[nx] = true;
				queue.addLast(new Location(nx, cur.cnt + 1));
			}
		}
	}
	
	public static boolean isUnreachable(int x) {
		return x < 0 || x > 100_000;
	}
}