import java.io.*;
import java.util.*;

public class Main {
	
	static class Camera {
		int y;
		int x;
		int type;
		int dir;
		
		Camera(int y, int x, int type, int dir) {
			this.y = y;
			this.x = x;
			this.type = type;
			this.dir = dir;
		}
	}
	
	static int N, M;
	static int K;
	static char[][] board;
	static List<Camera> cameraList;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = 0;
		
		cameraList = new ArrayList<>();
		board = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = st.nextToken().charAt(0);
				
				if(board[i][j] != '0' && board[i][j] != '6') {
					cameraList.add(new Camera(i, j, board[i][j] - '0', 1));
					K++;
				}
			}
		}
		
		solve(0);
		System.out.println(ans);
	}
	
	public static void solve(int selectedCamera) {
		if(selectedCamera == K) {
			ans = Math.min(ans, check());
			return;
		}
		
		if(cameraList.get(selectedCamera).type == 1) {
			for(int i = 0; i < 4; i++) {
				cameraList.get(selectedCamera).dir += i;
				solve(selectedCamera + 1);
				cameraList.get(selectedCamera).dir -= i;
			}
		} else if(cameraList.get(selectedCamera).type == 2) {
			for(int i = 0; i < 2; i++) {
				cameraList.get(selectedCamera).dir += i;
				solve(selectedCamera + 1);
				cameraList.get(selectedCamera).dir -= i;
			}
			
		} else if(cameraList.get(selectedCamera).type == 3) {
			for(int i = 0; i < 4; i++) {
				cameraList.get(selectedCamera).dir += i;
				solve(selectedCamera + 1);
				cameraList.get(selectedCamera).dir -= i;
			}
		} else if(cameraList.get(selectedCamera).type == 4) {
			for(int i = 0; i < 4; i++) {
				cameraList.get(selectedCamera).dir += i;
				solve(selectedCamera + 1);
				cameraList.get(selectedCamera).dir -= i;
			}
		} else if(cameraList.get(selectedCamera).type == 5) {
			solve(selectedCamera + 1);
		}
	}
	
	public static int check() {
		char[][] temp = new char[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = '0';
				
				if(board[i][j] == '6') {
					temp[i][j] = '6';
				}
				
				if(board[i][j] != '6' && board[i][j] != '0') {
					temp[i][j] = '#';
				}
			}
		}
		
		for(Camera camera : cameraList) {
			int y = camera.y;
			int x = camera.x;
			camera.dir = camera.dir < 0 ? (camera.dir + 4) % 4 : camera.dir % 4;
			
			if(camera.type == 1) {
				int dir = camera.dir;
				move(y, x, temp, dir);
			} else if(camera.type == 2) {
				int dir = camera.dir;
				
				for(int i = 0; i < 2; i++) {
					move(y, x, temp, dir);
					dir = (dir + 2) % 4;
				}
			} else if(camera.type == 3) {
				int dir = camera.dir;
				dir = (4 + dir - 1) % 4;
				
				for(int i = 0; i < 2; i++) {
					move(y, x, temp, dir);
					dir = (dir + 1) % 4;
				}
			} else if(camera.type == 4) {
				int dir = camera.dir;
				dir = (4 + dir - 2) % 4;
				
				for(int i = 0; i < 3; i++) {
					move(y, x, temp, dir);
					dir = (dir + 1) % 4;
				}
			} else if(camera.type == 5) {
				int dir = camera.dir;
				
				for(int i = 0; i < 4; i++) {
					move(y, x, temp, dir);
					dir = (dir + 1) % 4;
				}
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				//System.out.print(temp[i][j] + " ");
				if(temp[i][j] == '0') {
					cnt++;
				}
			}
			//System.out.println();
		}
		//System.out.println("=====");
		
		return cnt;
	}
	
	public static void move(int y, int x, char[][] temp, int dir) {
		while(true) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if(isUnreachable(ny, nx)) {
				break;
			}
			
			if(board[ny][nx] == '6') {
				temp[ny][nx] = '6';
				break;
			}
			
			temp[ny][nx] = '#';
			y = ny;
			x = nx;
		}
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}