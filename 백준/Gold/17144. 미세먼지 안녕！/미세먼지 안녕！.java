import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		int amount;
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		Location(int y, int x, int amount) {
			this(y, x);
			this.amount = amount;
		}
	}
	
	static int N, M, K;
	static int[][] board;
	static Location cleanerUp;
	static Location cleanerDown;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == -1) {
					if(cleanerUp == null) {
						cleanerUp = new Location(i, j);
					} else if(cleanerDown == null) {
						cleanerDown = new Location(i, j);
					}
				}
			}
		}
		
		for(int t = 0; t < K; t++) {
			spread();
			rotateUp();
			rotateDown();
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] > 0) {
					ans += board[i][j];
				}
			}
		}
		
		System.out.println(ans);
	}
	
	public static void spread() {
		Deque<Location> queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				
				int cnt = 0;
				if(board[i][j] > 0) {
					for(int dir = 0; dir < 4; dir++) {
						int ny = i + dy[dir];
						int nx = j + dx[dir];
						
						if(isUnreachable(ny, nx) || board[ny][nx] == -1) {
							continue;
						}
						
						cnt++;
						queue.addLast(new Location(ny, nx, board[i][j] / 5));
					}
				}
				
				board[i][j] = board[i][j] - ((board[i][j] / 5) * cnt);
			}
		}
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			board[cur.y][cur.x] += cur.amount;
		}
	}
	
	private static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
	
	public static void rotateUp() {
		// 청정기 바로 위의 먼지를 제거
		board[cleanerUp.y - 1][cleanerUp.x] = 0;
		
		// 청정기 위
		for(int i = cleanerUp.y - 1; i > 0; i--) {
			board[i][0] = board[i - 1][0];
		}
		
		// y = 0 axis
		for(int j = 0; j < M - 1; j++) {
			board[0][j] = board[0][j + 1];
		}
		
		// x = M - 1 axis
		for(int i = 0; i < cleanerUp.y; i++) {
			board[i][M - 1] = board[i + 1][M - 1];
		}
		
		// y = cleanerUp.y axis
		for(int j = M - 1; j > cleanerUp.x + 1; j--) {
			board[cleanerUp.y][j] = board[cleanerUp.y][j - 1];
		}
		
		board[cleanerUp.y][cleanerUp.x + 1] = 0;
	}
	
	public static void rotateDown() {
		// cleaner의 아래 먼지 제거
		board[cleanerDown.y + 1][cleanerDown.x] = 0;
		for(int i = cleanerDown.y + 1; i < N - 1; i++) {
			board[i][0] = board[i + 1][0];
		}
		
		for(int j = 0; j < M - 1; j++) {
			board[N - 1][j] = board[N - 1][j + 1];
		}
		
		for(int i = N - 1; i > cleanerDown.y; i--) {
			board[i][M - 1] = board[i - 1][M - 1];
		}
		
		for(int j = M - 1; j > cleanerDown.x + 1; j--) {
			board[cleanerDown.y][j] = board[cleanerDown.y][j - 1];
		}
		
		board[cleanerDown.y][cleanerDown.x + 1] = 0;
	}
}