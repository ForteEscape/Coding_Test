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
	
	static int[][] board;
	static int N, M;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static boolean[][] visited;
	static List<Location> virus;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		virus = new ArrayList<>();
		List<Location> emptyGround = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == 2) {
					virus.add(new Location(i, j));
				}
				
				if(board[i][j] == 0) {
					emptyGround.add(new Location(i, j));
				}
			}
		}
		
		int ans = -1;
		for(int i = 0; i < emptyGround.size() - 2; i++) {
			Location first = emptyGround.get(i);
			board[first.y][first.x] = 1;
			
			for(int j = i + 1; j < emptyGround.size() - 1; j++) {
				Location second = emptyGround.get(j);
				board[second.y][second.x] = 1;
				
				for(int k = j + 1; k < emptyGround.size(); k++) {
					Location third = emptyGround.get(k);
					board[third.y][third.x] = 1;
					
					ans = Math.max(ans, bfs());
					
					board[third.y][third.x] = 0;
				}
				board[second.y][second.x] = 0;
			}
			board[first.y][first.x] = 0;
		}
		
		System.out.println(ans);
	}
	
	public static int bfs() {
		Deque<Location> queue = new ArrayDeque<>();
		visited = new boolean[N][M];
		
		for(Location location : virus) {
			queue.addLast(new Location(location.y, location.x));
			visited[location.y][location.x] = true;
		}
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] == 1) {
					continue;
				}
				
				visited[ny][nx] = true;
				queue.addLast(new Location(ny, nx));
			}
		}
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 0 && !visited[i][j]) {
					result++;
				}
			}
		}
		
		return result;
	}
	
	private static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}