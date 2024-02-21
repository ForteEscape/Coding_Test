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
	
	static List<Location> meltCheese;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int[][] board;
	static int N, M;
	static int cheeseCnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}
		
		int ans = 1;
		while(true) {
			bfs(0, 0);
			if(cheeseCnt == 0) break;
			ans++;
		}
		
		System.out.println(ans);
	}
	
	public static void bfs(int y, int x) {
		boolean[][] visited = new boolean[N][M];
		int[][] touchCnt = new int[N][M];
		meltCheese = new ArrayList<>();
		
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(new Location(y, x));
		visited[y][x] = true;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx]) {
					continue;
				}
				
				if(board[ny][nx] == 1) {
					touchCnt[ny][nx]++;
					if(touchCnt[ny][nx] >= 2) {
						meltCheese.add(new Location(ny, nx));
						visited[ny][nx] = true;
					}
					continue;
				}
				
				visited[ny][nx] = true;
				queue.addLast(new Location(ny, nx));
			}
		}
		
		for(Location cheese : meltCheese) {
			board[cheese.y][cheese.x] = 0;
			cheeseCnt--;
		}
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}