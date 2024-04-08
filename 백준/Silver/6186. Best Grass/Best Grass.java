import java.util.*;
import java.io.*;

class Main {
	
	static class Pair {
		int y;
		int x;
		
		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, M;
	static int cnt;
	static char[][] board;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		int ans = 0;
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++){
				if(board[i][j] == '#' && !visited[i][j]) {
					ans++;
					visited[i][j] = true;
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int y, int x) {
		Deque<Pair> queue = new ArrayDeque<>();
		queue.addLast(new Pair(y, x));
		
		while(!queue.isEmpty()) {
			Pair cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] != '#') {
					continue;
				}
				
				visited[ny][nx] = true;
				queue.addLast(new Pair(ny, nx));
			}
		}
	}
	
	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
	
}