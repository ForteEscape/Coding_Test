import java.util.*;
import java.io.*;

class Main {
	
	static class Node {
		int maxCount;
		int cur;
		
		Node(int maxCount, int cur) {
			this.maxCount = maxCount;
			this.cur = cur;
		}
	}

	static int[][] board;
	static Node[][] dp;
	static int[] match = {1, 2, 0};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		dp = new Node[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cur = 0;
		if(board[0][0] == 0) {
			dp[0][0] = new Node(1, ++cur);
		} else {
			dp[0][0] = new Node(0, cur);
		}
		
		for(int i = 1; i < N; i++) {
			if(board[0][i] == cur) {
				dp[0][i] = new Node(dp[0][i - 1].maxCount + 1, (cur + 1) % 3);
				cur = (cur + 1) % 3;
			} else {
				dp[0][i] = new Node(dp[0][i - 1].maxCount, cur);
			}
		}
		
		cur = dp[0][0].cur;
		for(int i = 1; i < N; i++) {
			if(board[i][0] == cur) {
				dp[i][0] = new Node(dp[i - 1][0].maxCount + 1, (cur + 1) % 3);
				cur = (cur + 1) % 3;
			} else {
				dp[i][0] = new Node(dp[i - 1][0].maxCount, cur);
			}
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < N; j++) {
				int[] tmp = new int[2];
				if(board[i][j] == dp[i - 1][j].cur) {
					tmp[0] = dp[i - 1][j].maxCount + 1;
					tmp[1] = (dp[i - 1][j].cur + 1) % 3;
				} else {
					tmp[0] = dp[i - 1][j].maxCount;
					tmp[1] = dp[i - 1][j].cur;
				}
				
				int[] tmp2 = new int[2];
				if(board[i][j] == dp[i][j - 1].cur) {
					tmp2[0] = dp[i][j - 1].maxCount + 1;
					tmp2[1] = (dp[i][j - 1].cur + 1) % 3;
				} else {
					tmp2[0] = dp[i][j - 1].maxCount;
					tmp2[1] = dp[i][j - 1].cur;
				}
				
				if(tmp[0] >= tmp2[0]) {
					dp[i][j] = new Node(tmp[0], tmp[1]);
				} else {
					dp[i][j] = new Node(tmp2[0], tmp2[1]);
				}
			}
		}
		
		System.out.println(dp[N - 1][N - 1].maxCount);
	}
}