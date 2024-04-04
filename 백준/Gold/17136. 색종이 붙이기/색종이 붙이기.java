import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
	static int[][] board;
	static int[] amount = { 0, 5, 5, 5, 5, 5 };
	static int ans = Integer.MAX_VALUE;

	// 좌측 상단을 기준으로 한다.

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		board = new int[10][10];

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(0, 0, 0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void solve(int y, int x, int cnt) {
		if(y == 9 && x == 10) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(ans <= cnt) {
			return;
		}
		
		if(x >= 10) {
			solve(y + 1, 0, cnt);
			return;
		}
		
		if(board[y][x] == 1) {
			for(int i = 5; i >= 1; i--) {
				if(amount[i] > 0 && isAttachable(y, x, i)) {
					amount[i]--;
					attach(y, x, i);
					
					solve(y, x + 1, cnt + 1);
					
					detach(y, x, i);
					amount[i]++;
				}
			}
		} else {
			solve(y, x + 1, cnt);
		}
	}
	
	static boolean isAttachable(int y, int x, int size) {
		for(int i = y; i < y + size; i++) {
			for(int j = x; j < x + size; j++) {
				if(isUnreachable(i, j)) {
					return false;
				}
				
				if(board[i][j] != 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void attach(int y, int x, int size) {
		for(int i = y; i < y + size; i++) {
			for(int j = x; j < x + size; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	static void detach(int y, int x, int size) {
		for(int i = y; i < y + size; i++) {
			for(int j = x; j < x + size; j++) {
				board[i][j] = 1;
			}
		}
	}
	
	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= 10 || x < 0 || x >= 10;
	}
}