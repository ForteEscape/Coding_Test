import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static char[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		board = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		solve(0, 0, N - 1, N - 1);
		
		System.out.println(sb);
	}
	
	public static void solve(int startY, int startX, int endY, int endX) {
		if(chk(startY, startX, endY, endX)) {
			sb.append(board[startY][startX]);
			return;
		}
		
		sb.append("(");
		
		int midY = (startY + endY) / 2;
		int midX = (startX + endX) / 2;
		
		//좌상단
		solve(startY, startX, midY, midX);
		//우상단
		solve(startY, midX + 1, midY, endX);
		//좌하단
		solve(midY + 1, startX, endY, midX);
		//우하단
		solve(midY + 1, midX + 1, endY, endX);
		
		sb.append(")");
	}
	
	public static boolean chk(int startY, int startX, int endY, int endX) {
		if(startY == endY && startX == endX) {
			return true;
		}
		
		char tgt = board[startY][startX];
		
		for(int i = startY; i <= endY; i++) {
			for(int j = startX; j <= endX; j++) {
				if(board[i][j] != tgt) {
					return false;
				}
			}
		}
		
		return true;
	}
}