import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		int time;
		
		Location(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	static int N, M;
	static char[][] board;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static Deque<Location> queue;
	static Deque<Location> waterQueue;
	static Location goal;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		Location player = null;
		queue = new ArrayDeque<>();
		waterQueue = new ArrayDeque<>();
		visited = new boolean[N][M];
		goal = null;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
				
				if(board[i][j] == 'S') {
					player = new Location(i, j, 0);
					queue.addLast(player);
					visited[i][j] = true;
				}
				
				if(board[i][j] == '*') {
					waterQueue.addLast(new Location(i, j, 0));
					visited[i][j] = true;
				}
				
				if(board[i][j] == 'D') {
					goal = new Location(i, j, 0);
				}
			}
		}
		
		while(true) {
			int result = bfs();
			if(result== -1) {
				System.out.println("KAKTUS");
				break;
			}
			
			if(result != 0) {
				System.out.println(result);
				break;
			}
			
			//print();
		}
	}
	
//	public static void print() {
//		for(int i = 0; i<  N; i++) {
//			for(int j = 0; j< M; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("======");
//	}
	
	public static int bfs() {
		if(queue.isEmpty()) {
			return -1;
		}
		
		// biber
		int size = queue.size();
		for(int i = 0; i < size; i++) {
			Location cur = queue.pollFirst();
			
			if(cur.y == goal.y && cur.x == goal.x) {
				return cur.time;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] == '*' || board[ny][nx] == 'X' || !isSafe(ny, nx)) {
					continue;
				}
				
				board[ny][nx] = 'S';
				visited[ny][nx] = true;
				queue.addLast(new Location(ny, nx, cur.time + 1));
			}
		}
		
		// water
		int waterSize = waterQueue.size();
		for(int i = 0; i < waterSize; i++) {
			Location water = waterQueue.pollFirst();
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = water.y + dy[dir];
				int nx = water.x + dx[dir];
				
				if(isUnreachable(ny, nx) || board[ny][nx] == 'D' || board[ny][nx] == 'X') {
					continue;
				}
				
				if(board[ny][nx] == 'S') {
					board[ny][nx] = '*';
					waterQueue.addLast(new Location(ny, nx, 0));
					continue;
				}
				
				if(visited[ny][nx]) {
					continue;
				}
				
				board[ny][nx] = '*';
				visited[ny][nx] = true;
				waterQueue.addLast(new Location(ny, nx, 0));
			}
		}
		
		return 0;
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
	
	public static boolean isSafe(int y, int x) {
		if(board[y][x] == 'D') return true;
		
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(isUnreachable(ny, nx)) {
				continue;
			}
			
			if(board[ny][nx] == '*') {
				return false;
			}
		}
		
		return true;
	}
}