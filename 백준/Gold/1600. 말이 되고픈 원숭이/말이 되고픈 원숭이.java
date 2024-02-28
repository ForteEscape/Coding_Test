import java.io.*;
import java.util.*;

public class Main {

	static class Location {
		int y;
		int x;
		int jmpCnt;
		int cnt;

		Location(int y, int x, int jmpCnt, int cnt) {
			this.y = y;
			this.x = x;
			this.jmpCnt = jmpCnt;
			this.cnt = cnt;
		}
	}
	
	static int N, M, K;
	static int[][] board;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int[] jmpDy = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] jmpDx = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(0, 0));
	}
	
	public static int bfs(int y, int x) {
		Deque<Location> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][K + 1];
		
		queue.addLast(new Location(y, x, 0, 0));
		visited[y][x][0] = true;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			if(cur.y == N - 1 && cur.x == M - 1) {
				return cur.cnt;
			}
			
			if(cur.jmpCnt < K) {
				for(int i = 0; i < 8; i++) {
					int ny = cur.y + jmpDy[i];
					int nx = cur.x + jmpDx[i];
					
					if(isUnreachable(ny, nx) || board[ny][nx] == 1 || visited[ny][nx][cur.jmpCnt + 1]) {
						continue;
					}
					
					visited[ny][nx][cur.jmpCnt + 1] = true;
					queue.addLast(new Location(ny, nx, cur.jmpCnt + 1, cur.cnt + 1));
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || board[ny][nx] == 1 || visited[ny][nx][cur.jmpCnt]) {
					continue;
				}
				
				visited[ny][nx][cur.jmpCnt] = true;
				queue.addLast(new Location(ny, nx, cur.jmpCnt, cur.cnt + 1));
			}
		}
		
		return -1;
	}
	
	private static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}