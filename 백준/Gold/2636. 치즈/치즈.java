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
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int[][] board;
	static boolean[][] visited;
	static List<Location> meltCheeseList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		int remainSize = -1;
		while(true) {
			int cheeseSize = bfs(0, 0);
			
			if(cheeseSize == 0) {
				break;
			}
			
			for(Location l : meltCheeseList) {
				board[l.y][l.x] = 0; 
			}
			
			remainSize = cheeseSize;
			time++;
		}
		
		System.out.println(time);
		System.out.println(remainSize);
	}
	
	public static int bfs(int y, int x) {
		Deque<Location> queue = new ArrayDeque<>();
		visited = new boolean[N][M];
		
		visited[y][x] = true;
		queue.addLast(new Location(y, x));
		
		meltCheeseList = new ArrayList<>();
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i<  4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx]) {
					continue;
				}
				
				visited[ny][nx] = true;
				if(board[ny][nx] == 1) {
					meltCheeseList.add(new Location(ny, nx));
					cnt++;
				} else {
					queue.addLast(new Location(ny, nx));
				}
			}
		}
		
		return cnt;
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}