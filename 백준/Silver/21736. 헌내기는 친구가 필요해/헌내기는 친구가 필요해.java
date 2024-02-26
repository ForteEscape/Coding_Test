import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, M;
	static boolean[][] visited;
	static char[][] board;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static Location player;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
				
				if(board[i][j] == 'I') {
					player = new Location(i, j);
				}
			}
		}
		
		visited = new boolean[N][M];
		
		int ans = bfs();
		System.out.println(ans == 0 ? "TT" : ans);	
		
	}
	
	public static int bfs() {
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(player);
		visited[player.y][player.x] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] == 'X') {
					continue;
				}
				
				visited[ny][nx] = true;
				if(board[ny][nx] == 'P') {
					cnt++;
				}
				queue.addLast(new Location(ny, nx));
			}
		}
		
		return cnt;
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}