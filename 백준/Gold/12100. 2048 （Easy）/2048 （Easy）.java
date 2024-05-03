import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] board;
	static boolean[][] merged;
	static int ans = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, new ArrayList<>());
		
		System.out.println(ans);
	}
	
	// 1 up 2 right 3 down 4 left
	static void solve(int cur, List<Integer> order) {
		if(cur == 5) {
			int[][] tempBoard = new int[N][N];
			
			init(tempBoard);
			
			for(int opt : order) {
				if(opt == 1) {
					up(tempBoard);
				} else if(opt == 2) {
					right(tempBoard);
				} else if(opt == 3) {
					down(tempBoard);
				} else if(opt == 4) {
					left(tempBoard);
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					ans = Math.max(ans, tempBoard[i][j]);
				}
			}
			
			return;
		}
		
		for(int i = 1; i <= 4; i++) {
			order.add(i);
			solve(cur + 1, order);
			order.remove(order.size() - 1);
		}
	}
	
	static void init(int[][] tempBoard) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
	}
	
	static void up(int[][] tempBoard) {
		merged = new boolean[N][N];
		
		for(int col = 0; col < N; col++) {
			for(int row = 1; row < N; row++) {
				if(tempBoard[row][col] == 0) continue;
				
				int currentRow = row;
				while(currentRow > 0) {
					if(tempBoard[currentRow - 1][col] != 0) {
						if(tempBoard[currentRow - 1][col] == tempBoard[currentRow][col] && !merged[currentRow - 1][col]) {
							merged[currentRow - 1][col] = true;
							tempBoard[currentRow - 1][col] = (tempBoard[currentRow - 1][col] << 1);
							tempBoard[currentRow][col] = 0;
						}
						break;
					} else {
						tempBoard[currentRow - 1][col] = tempBoard[currentRow][col];
						tempBoard[currentRow][col] = 0;
					}
					
					currentRow--;
				}
				
			}
		}
	}
	
	static void down(int[][] tempBoard) {
		merged = new boolean[N][N];
		
		for(int col = 0; col < N; col++) {
			for(int row = N - 2; row >= 0; row--) {
				if(tempBoard[row][col] == 0) continue;
				
				int currentRow = row;
				while(currentRow < N - 1) {
					if(tempBoard[currentRow + 1][col] != 0) {
						if(tempBoard[currentRow + 1][col] == tempBoard[currentRow][col] && !merged[currentRow + 1][col]) {
							merged[currentRow + 1][col] = true;
							tempBoard[currentRow + 1][col] = (tempBoard[currentRow + 1][col] << 1);
							tempBoard[currentRow][col] = 0;
						}
						break;
					} else {
						tempBoard[currentRow + 1][col] = tempBoard[currentRow][col];
						tempBoard[currentRow][col] = 0;
					}
					
					currentRow++;
				}
			}
		}
	}
	
	static void left(int[][] tempBoard) {
		merged = new boolean[N][N];
		
		for(int row = 0; row < N; row++) {
			for(int col = 1; col < N; col++) {
				if(tempBoard[row][col] == 0) continue;
				
				int currentCol = col;
				while(currentCol > 0) {
					if(tempBoard[row][currentCol - 1] != 0) {
						if(tempBoard[row][currentCol - 1] == tempBoard[row][currentCol] && !merged[row][currentCol - 1]) {
							merged[row][currentCol - 1] = true;
							tempBoard[row][currentCol - 1] = (tempBoard[row][currentCol - 1] << 1);
							tempBoard[row][currentCol] = 0;
						}
						break;
					} else {
						tempBoard[row][currentCol - 1] = tempBoard[row][currentCol];
						tempBoard[row][currentCol] = 0;
					}
					
					currentCol--;
				}
			}
		}
	}
	
	static void right(int[][] tempBoard) {
		merged = new boolean[N][N];
		
		for(int row = 0; row < N; row++) {
			for(int col = N - 2; col >= 0; col--) {
				if(tempBoard[row][col] == 0) continue;
				
				int currentCol = col;
				while(currentCol < N - 1) {
					if(tempBoard[row][currentCol + 1] != 0) {
						if(tempBoard[row][currentCol + 1] == tempBoard[row][currentCol] && !merged[row][currentCol + 1]) {
							merged[row][currentCol + 1] = true;
							tempBoard[row][currentCol + 1] = (tempBoard[row][currentCol + 1] << 1);
							tempBoard[row][currentCol] = 0;
						}
						break;
					} else {
						tempBoard[row][currentCol + 1] = tempBoard[row][currentCol];
						tempBoard[row][currentCol] = 0;
					}
					
					currentCol++;
				}
			}
		}
	}
	
}