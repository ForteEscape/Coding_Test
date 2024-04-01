import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static class Pair {
		int y;
		int x;
		
		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, M, K;
	static int[][] board;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N + 1][M + 2];
		for(int i = 0; i < M + 2; i++) {
			board[0][i] = 0;
		}
		
		for(int i = 1; i <= N; i++) {
			board[i][0] = 0;
			board[i][M + 1] = 0;
		}
		
		int left = Integer.MAX_VALUE;
		int right = Integer.MIN_VALUE;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				left = Math.min(left, board[i][j]);
				right = Math.max(right, board[i][j]);
			}
		}
		
		while(left < right) {
			int mid = (left + right) >> 1;
		
			int result = bfs(mid);
			
			if(result >= K) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(right);
	}
	
	static int bfs(int std) {
		visited = new boolean[N + 1][M + 2];
		int result = 0;
		
		Deque<Pair> queue = new ArrayDeque<>();
		queue.addLast(new Pair(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Pair cur = queue.pollFirst();
			
			if(board[cur.y][cur.x] > 0) {
				result++;
			}
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] > std) {
					continue;
				}
				
				visited[ny][nx] = true;
				queue.addLast(new Pair(ny, nx));
			}
		}
		
		return result;
	}
	
	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N + 1 || x < 0 || x >= M + 2;
	}
}