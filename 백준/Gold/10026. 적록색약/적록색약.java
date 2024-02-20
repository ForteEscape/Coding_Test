import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
//8 directions
public class Main {
	
	static class Location {
		int y;
		int x;
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N;
	static char[][] board;
	static char[][] board2;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		board = new char[N][N];
		board2 = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j);
				
				if(board[i][j] == 'G') {
					board2[i][j] = 'R';
				} else {
					board2[i][j] = board[i][j];
				}
			}
		}
		
		int ans = 0;
		for(int i = 0; i<  N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					ans++;
					bfs(i, j, board);
				}
			}
		}
		
		visited = new boolean[N][N];
		int ans2 = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					ans2++;
					bfs(i, j, board2);
				}
			}
		}
		
		System.out.println(ans + " " + ans2);
	}
	
	public static void bfs(int y, int x, char[][] board) {
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(new Location(y, x));
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[cur.y][cur.x] != board[ny][nx]) {
					continue;
				}
				
				visited[ny][nx] = true;
				queue.addLast(new Location(ny, nx));
			}
		}
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}