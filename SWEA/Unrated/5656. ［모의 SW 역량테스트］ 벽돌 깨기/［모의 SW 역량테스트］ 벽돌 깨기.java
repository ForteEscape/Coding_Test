import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int[][] board;
	static int N, W, H;
	static int ans;
	static List<List<Integer>> perm;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			board = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			perm = new ArrayList<>();

			ans = Integer.MAX_VALUE;
			solve(0, new ArrayList<>());

			for (int i = 0; i < perm.size(); i++) {
				int[][] simulBoard = cloneData();

				for (int j = 0; j < N; j++) {
					int dropLocation = perm.get(i).get(j);

					drop(simulBoard, dropLocation);
				}
				
				ans = Math.min(ans, count(simulBoard));			
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int count(int[][] board) {
		int cnt = 0;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(board[i][j] != 0) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	static void drop(int[][] board, int col) {

		for (int i = 0; i < H; i++) {
			if (board[i][col] != 0) {
				destroy(i, col, board);
				gravity(board);
				break;
			}
		}
	}
	
	static void gravity(int[][] board) {
		for(int i = 0; i < W; i++) {
			int idx = H - 1;
			int[] newCol = new int[H];
			
			for(int j = H - 1; j >= 0; j--) {
				if(board[j][i] != 0) {
					newCol[idx--] = board[j][i];
				}
			}
			
			for(int j = 0; j < H; j++) {
				board[j][i] = newCol[j];
			}
		}
	}
	
	static void destroy(int y, int x, int[][] board) {
		if(board[y][x] < 2) {
			board[y][x] = 0;
			return;
		}
		
		int time = board[y][x];
		board[y][x] = 0;
		
		for(int i = 0; i < 4; i++) {
			int yy = y;
			int xx = x;
			
			for(int j = 0; j < time - 1; j++) {
				int ny = yy + dy[i];
				int nx = xx + dx[i];
				
				if(isUnreachable(ny, nx)) {
					continue;
				}
				
				destroy(ny, nx, board);
				
				yy = ny;
				xx = nx;
			}
		}
	}

	static int[][] cloneData() {
		int[][] tmp = new int[H][W];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = board[i][j];
			}
		}

		return tmp;
	}

	static void solve(int cur, List<Integer> seq) {
		if (cur == N) {
			List<Integer> result = new ArrayList<>();
			for (int element : seq) {
				result.add(element);
			}

			perm.add(result);
			return;
		}

		for (int i = 0; i < W; i++) {
			seq.add(i);
			solve(cur + 1, seq);
			seq.remove(seq.size() - 1);
		}
	}
	
	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= H || x < 0 || x >= W;
	}
}