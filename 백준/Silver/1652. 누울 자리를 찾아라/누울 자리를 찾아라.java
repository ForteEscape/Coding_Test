import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static char[][] board;
	static int N;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		board = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		int horizontalCnt = 0;
		int verticalCnt = 0;
		
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == '.' && !visited[i][j]) {
					int k = j;
					int cnt = 0;
					while(k < N && board[i][k] != 'X') {
						visited[i][k] = true;
						cnt++;
						k++;
					}
					
					if(cnt > 1) {
						horizontalCnt++;
					}
				}
			}
		}
		
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[j][i] == '.' && !visited[j][i]) {
					int k = j;
					int cnt = 0;
					while(k < N && board[k][i] != 'X') {
						visited[k][i] = true;
						cnt++;
						k++;
					}
					
					if(cnt > 1) {
						verticalCnt++;
					}
				}
			}
		}
		
		System.out.println(horizontalCnt + " " + verticalCnt);
	}
}