import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static class Location {
		int y;
		int x;
		int direction;
		
		Location(int y, int x, int direction) {
			this.y = y;
			this.x = x;
			this.direction = direction;
		}
	}
	
	static boolean[][] dust;
	static int N, M;
	static int[][][] commandCode;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int arisY = Integer.parseInt(st.nextToken());
		int arisX = Integer.parseInt(st.nextToken());
		int arisDirection = Integer.parseInt(st.nextToken());
		
		Location aris = new Location(arisY, arisX, arisDirection);
		
		dust = new boolean[N][M];
		commandCode = new int[2][N][M];
		visited = new boolean[4][N][M];
		
		for(int k = 0; k < 2; k++) {
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < M; j++) {
					commandCode[k][i][j] = line.charAt(j) - '0';
				}
			}
		}
		
		int ans = 0;
		int time = 0;
		boolean flag = false;
		while(true) {
			time++;
			if(!dust[aris.y][aris.x]) {
				dust[aris.y][aris.x] = true;
				aris.direction = (aris.direction + commandCode[0][aris.y][aris.x]) % 4;
				flag = false;
				ans = time;
			} else {
				flag = true;
				aris.direction = (aris.direction + commandCode[1][aris.y][aris.x]) % 4;
			}
			
			int ny = aris.y + dy[aris.direction];
			int nx = aris.x + dx[aris.direction];
			
			aris.y = ny;
			aris.x = nx;
			
			if(isUnreachable(aris.y, aris.x) || visited[aris.direction][aris.y][aris.x]) {
				break;
			}
			
			visited[aris.direction][aris.y][aris.x] = true;
			if(!flag) {
				visited = new boolean[4][N][M];
			}
		}
		
		System.out.println(ans);
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}