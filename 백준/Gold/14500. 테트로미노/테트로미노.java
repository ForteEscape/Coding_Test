import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dummy4 = {
			{1, 0},
			{1, 0},
			{1, 1}
	};
	
	static int[][] dummy5 = {
			{0, 1},
			{0, 1},
			{1, 1}
	};
	
	static int[][] dummy6 = {
			{1, 1},
			{0, 1},
			{0, 1}
	};
	
	static int[][] dummy7 = {
			{1, 1},
			{1, 0},
			{1, 0}
	};
	
	static int[][] dummy8 = {
			{0, 0, 1},
			{1, 1, 1}
	};
	
	static int[][] dummy9 = {
			{1, 0, 0},
			{1, 1, 1}
	};
	
	static int[][] dummy10 = {
			{1, 1, 1},
			{0, 0, 1}
	};
	
	static int[][] dummy11 = {
			{1, 1, 1},
			{1, 0, 0}
	};

	static int[][] dummy12 = {
			{1, 0},
			{1, 1},
			{0, 1}
	};
	
	static int[][] dummy13 = {
			{0, 1},
			{1, 1},
			{1, 0}
	};
	
	static int[][] dummy14 = {
			{1, 1, 0},
			{0, 1, 1}
	};
	
	static int[][] dummy15 = {
			{0, 1, 1},
			{1, 1, 0}
	};
	
	static int[][] dummy16 = {
			{1, 1, 1},
			{0, 1, 0}
	};
	
	static int[][] dummy17 = {
			{0, 1, 0},
			{1, 1, 1}
	};
	
	static int[][] dummy18 = {
			{0, 1},
			{1, 1},
			{0, 1}
	};
	
	static int[][] dummy19 = {
			{1, 0},
			{1, 1},
			{1, 0}
	};
	
	static int[][][] Lbox = {
			dummy4, dummy5, dummy6, dummy7
	};
	
	static int[][][] LSwipedBox = {
			dummy8, dummy9, dummy10, dummy11
	};
	
	static int[][][] zigBox = {
			dummy12, dummy13
	};
	
	static int[][][] zigSwippedBox = {
			dummy14, dummy15
	};
	
	static int[][][] Tbox = {
			dummy16, dummy17
	};
	
	static int[][][] TSwippedBox = {
			dummy18, dummy19
	};
	
	static int[][] board;
	static int N, M;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MIN_VALUE;
		
		barTest();
		boxTest();
		BlockTest(Lbox);
		BlockTest(zigBox);
		BlockTest(TSwippedBox);
		
		SwippedBoxTest(LSwipedBox);
		SwippedBoxTest(zigSwippedBox);
		SwippedBoxTest(Tbox);
		
		System.out.println(ans);
	}
	
	public static void barTest() {
		int result = 0;
		
		for(int i = 0; i < N ; i++) {
			for(int j = 0; j <= M - 4; j++) {
				for(int k = 0; k < 4; k++) {
					result += board[i][j + k];
				}
				
				ans = Math.max(ans, result);
				result = 0;
			}
		}
		
		for(int i = 0; i < M ; i++) {
			for(int j = 0; j <= N - 4; j++) {
				for(int k = 0; k < 4; k++) {
					result += board[j + k][i];
				}
				
				ans = Math.max(ans, result);
				result = 0;
			}
		}
	}
	
	public static void boxTest() {
		int result = 0;
		
		for(int i = 0; i <= N - 2; i++) {
			for(int j = 0; j <= M - 2; j++) {
				
				for(int k = 0; k < 2; k++) {
					for(int l = 0; l < 2; l++) {
						result += board[i + k][j + l];
					}
				}
				
				ans = Math.max(ans, result);
				result = 0;
			}
		}
	}
	
	// i = 3 x 2
	public static void BlockTest(int[][][] box) {
		int result = 0;
		
		for(int i = 0; i < box.length; i++) {
			for(int j = 0; j <= N - 3; j++) {
				for(int k = 0; k <= M - 2; k++) {
					
					for(int l = 0; l < 3; l++) {
						for(int s = 0; s < 2; s++) {
							if(box[i][l][s] == 1) {
								result += board[j + l][k + s];
							}
						}
					}
					
					ans = Math.max(ans, result);
					result = 0;
				}
			}
		}
	}
	
	// 2 x 3
	public static void SwippedBoxTest(int[][][] box) {
		int result = 0;

		for(int i = 0; i < box.length; i++) {
			for(int j = 0; j <= N - 2; j++) {
				for(int k = 0; k <= M - 3; k++) {
					
					for(int l = 0; l < 2; l++) {
						for(int s = 0; s < 3; s++) {
							if(box[i][l][s] == 1) {
								result += board[j + l][k + s];
							}
						}
					}
					
					ans = Math.max(ans, result);
					result = 0;
				}
			}
		}	
	}
}