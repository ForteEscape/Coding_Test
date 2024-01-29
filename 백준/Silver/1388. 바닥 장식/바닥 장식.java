import java.util.*;
import java.io.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		
		Location(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	public static int N;
	public static int M;
	public static char[][] board;
	public static boolean[][] visited;
	
	public static int[] dy = {1, -1};
	public static int[] dx = {1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < line.length(); j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					if(board[i][j] == '|') {
						ans++;
						bfs(i, j, '|');
					} else {
						ans++;
						bfs(i, j, '-');
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	public static void bfs(int y, int x, char type) {
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(new Location(y, x));
		visited[y][x] = true;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			if(type == '|') {
				for(int i = 0; i < 2; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x;
					
					if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] != type) {
						continue;
					}
					
					visited[ny][nx] = true;
					queue.addLast(new Location(ny, nx));
				}
			} else {
				for(int i = 0; i < 2; i++) {
					int ny = cur.y;
					int nx = cur.x + dx[i];
					
					if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] != type) {
						continue;
					}
					
					visited[ny][nx] = true;
					queue.addLast(new Location(ny, nx));
				}
			}
		}
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
