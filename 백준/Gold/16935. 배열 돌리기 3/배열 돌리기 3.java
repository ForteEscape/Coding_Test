import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] board;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int cmd = Integer.parseInt(st.nextToken());

			if (cmd == 1) {
				reverseVertical();
			} else if (cmd == 2) {
				reverseHorizontal();
			} else if (cmd == 3) {
				rotateRight();
			} else if (cmd == 4) {
				rotateLeft();
			} else if (cmd == 5) {
				rotateGroup1();
			} else if (cmd == 6) {
				rotateGroup2();
			}
		}
		
		print();
	}

	public static void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void reverseVertical() {
		int[][] temp = new int[board.length][board[0].length];

		for (int i = N - 1, idx = 0; i >= 0; i--, idx++) {
			for (int j = 0; j < M; j++) {
				temp[idx][j] = board[i][j];
			}
		}

		board = temp;
	}

	public static void reverseHorizontal() {
		int[][] temp = new int[board.length][board[0].length];

		for (int i = 0; i < N; i++) {
			for (int j = M - 1, idx = 0; j >= 0; j--, idx++) {
				temp[i][idx] = board[i][j];
			}
		}

		board = temp;
	}

	public static void rotateRight() {
		int[][] temp = new int[board[0].length][board.length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				temp[j][board.length - i - 1] = board[i][j];
			}
		}

		board = temp;
		int tempData = N;
		N = M;
		M = tempData;
	}

	public static void rotateLeft() {
		int[][] temp = new int[board[0].length][board.length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				temp[board[0].length - j - 1][i] = board[i][j];
			}
		}

		board = temp;
		int tempData = N;
		N = M;
		M = tempData;
	}

	public static void rotateGroup1() {
		int[][] temp = new int[board.length][board[0].length];

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[i][j + M / 2] = board[i][j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for(int j = M / 2; j < M; j++) {
				temp[N / 2 + i][j] = board[i][j];
			}
		}
		
		for(int i = N / 2; i < N; i++) {
			for(int j = M / 2; j < M; j++) {
				temp[i][j - M / 2] = board[i][j];
			}
		}
		
		for(int i = N / 2; i < N; i++) {
			for(int j = 0; j < M / 2; j++) {
				temp[i - N / 2][j] = board[i][j];
			}
		}
		
		board = temp;
	}

	public static void rotateGroup2() {
		int[][] temp = new int[board.length][board[0].length];

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[N / 2 + i][j] = board[i][j];
			}
		}
		
		for(int i = N / 2; i < N; i++) {
			for(int j = 0; j < M / 2; j++) {
				temp[i][j + M / 2] = board[i][j];
			}
		}
		
		for(int i = N / 2; i < N; i++) {
			for(int j = M / 2; j < M; j++) {
				temp[i - N / 2][j] = board[i][j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for(int j = M / 2; j < M; j++) {
				temp[i][j - M / 2] = board[i][j];
			}
		}
		
		board = temp;
	}
}