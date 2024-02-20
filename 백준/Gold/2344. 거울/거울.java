import java.io.*;
import java.util.*;

public class Main {
	
	static class Light {
		int y;
		int x;
		int dir;
		
		Light(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	
	static class Hole {
		int y;
		int x;
		int idx;
		
		Hole(int y, int x, int idx) {
			this.y = y;
			this.x = x;
			this.idx = idx;
		}
	}
	
	static int N, M;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	static int[][] board;
	static Hole[] holes;
	static int[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N + 2][M + 2];
		holes = new Hole[2 * N + 2 * M + 1];
		
		init();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) {
					board[i][j] = -1;
				}
			}
		}
		
		ans = new int[2 * N + 2 * M + 1];
		
		for(int i = 1; i <= N + M; i++) {
			Light light = null;
			
			if(i <= N) {
				light = new Light(holes[i].y, holes[i].x + 1, 3);
			} else {
				light = new Light(holes[i].y - 1, holes[i].x, 0);
			}
			
			while(true) {
				if(board[light.y][light.x] == -1) {
					if(light.dir == 3) {
						light.dir = 0;
					} else if(light.dir == 0) {
						light.dir = 3;
					} else if(light.dir == 1) {
						light.dir = 2;
					} else if(light.dir == 2) {
						light.dir = 1;
					}
				}
				
				int ny = light.y + dy[light.dir];
				int nx = light.x + dx[light.dir];
				
				if(isUnreachable(ny, nx)) {
					for(int j = 1; j < holes.length; j++) {
						if(holes[j].y == ny && holes[j].x == nx) {
							ans[i] = j;
							ans[j] = i;
							break;
						}
					}
					
					break;
				}
				
				light.y = ny;
				light.x = nx;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= 2 * N + 2 * M; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 1 || y > N || x < 1 || x > M;
	}
	
	public static void init() {
		int idx = 1;
		for(int i = 1; i <= N; i++) {
			board[i][0] = idx;
			holes[idx] = new Hole(i, 0, idx);
			idx++;
		}
		
		for(int i = 1; i <= M; i++) {
			board[N + 1][i] = idx;
			holes[idx] = new Hole(N + 1, i, idx);
			idx++;
		}
		
		for(int i = N; i >= 1; i--) {
			board[i][M + 1] = idx;
			holes[idx] = new Hole(i, M + 1, idx);
			idx++;
		}
		
		for(int i = M; i >= 1; i--) {
			board[0][i] = idx;
			holes[idx] = new Hole(0, i, idx);
			idx++;
		}
	}
	

}