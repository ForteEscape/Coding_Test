import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		board = new int[101][101];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			for(int j = l; j < l + 10; j++) {
				for(int k = r; k < r + 10; k++) {
					board[j][k] = 1;
				}
			}
		}
		
		int ans = 0;
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(board[i][j] == 1) {
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
}