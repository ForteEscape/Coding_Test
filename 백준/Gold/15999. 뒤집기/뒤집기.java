import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static char[][] board;
	static int[][] check;
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		check = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(j + 1 < M && board[i][j] != board[i][j + 1]) {
					check[i][j] = 1;
					check[i][j + 1] = 1;
				}
				
				if(i + 1 < N && board[i][j] != board[i + 1][j]) {
					check[i][j] = 1;
					check[i + 1][j] = 1;
				}
			}
		}
		
		int notChangeableCnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(check[i][j] == 1) {
					notChangeableCnt++;
				}
			}
		}
		
		long res = getPow(2, N * M - notChangeableCnt) % MOD;
		System.out.println(res);
	}
	
	static long getPow(int a, int b) {
		if(b == 0) {
			return 1;
		}
		
		long res = getPow(a, b / 2);
		
		if(b % 2 == 0) {
			return (res % MOD * res % MOD) % MOD;
		} else {
			return ((a % MOD * res % MOD) % MOD * res % MOD) % MOD;
		}
	}
}