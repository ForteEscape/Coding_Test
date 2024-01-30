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
	
	public static final int INF = Integer.MAX_VALUE;
	public static int[][] board;
	public static int[][] dist;
	public static int[] dy = {1, -1, 0, 0};
	public static int[] dx = {0, 0, 1, -1};
	
	public static int N;
	public static int M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		dist = new int[N][M];
		
		Location start = null;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == 2) {
					start = new Location(i, j);
				}
			}
			
			Arrays.fill(dist[i], INF);
		}
		
		bfs(start);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] != 0) {
					if(dist[i][j] == INF) {
						System.out.print("-1 ");
					} else {
						System.out.print(dist[i][j] + " ");
					}
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}
	
	public static void bfs(Location start) {
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(start);
		dist[start.y][start.x] = 0;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || board[ny][nx] == 0 || dist[ny][nx] != INF) {
					continue;
				}
				
				dist[ny][nx] = dist[cur.y][cur.x] + 1;
				queue.addLast(new Location(ny, nx));
			}
		}
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
