import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		int dir;
		int cnt;
		
		Location(int y, int x, int dir, int cnt) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
		
	}
	
	static int[][] board;
	static boolean[][][] visited;
	static int N, M;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static Location robot;
	static Location dest;
	static int[][] temp;

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
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		robot = new Location(y - 1, x - 1, dir, 0);
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		dest = new Location(y - 1, x - 1, dir, 0);
		
		System.out.println(bfs());
	}
	
	public static int bfs() {
		Deque<Location> queue = new ArrayDeque<>();
		visited = new boolean[N][M][5];
		
		queue.addLast(robot);
		visited[robot.y][robot.x][robot.dir] = true;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			if(cur.y == dest.y && cur.x == dest.x) {
				if(cur.dir == dest.dir) {
					return cur.cnt;
				}
			}
			
			for(int i = 1; i <= 3; i++) {
				int ny = cur.y + (dy[cur.dir - 1] * i);
				int nx = cur.x + (dx[cur.dir - 1] * i);
				
				if(isUnreachable(ny, nx) || visited[ny][nx][cur.dir]) {
					continue;
				}
				
				if(board[ny][nx] == 1) {
					break;
				}
				
				visited[ny][nx][cur.dir] = true;
				queue.addLast(new Location(ny, nx, cur.dir, cur.cnt + 1));
			}
			
			for(int i = 1; i <= 4; i++) {
				if(cur.dir != i && !visited[cur.y][cur.x][i]) {
					int turn = 1;
					
					if(cur.dir == 1) {
						if(i == 2) {
							turn++;
						}
					} else if(cur.dir == 2) {
						if(i == 1) {
							turn++;
						}
					} else if(cur.dir == 3) {
						if(i == 4) {
							turn++;
						}
					} else if(cur.dir == 4) {
						if(i == 3) {
							turn++;
						}
					}
					
					visited[cur.y][cur.x][i] = true;
					queue.addLast(new Location(cur.y, cur.x, i, cur.cnt + turn));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}