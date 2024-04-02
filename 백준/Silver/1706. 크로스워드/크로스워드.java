import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static char[][] board;
	static int N, M;
	static boolean[][] visited;
	static TreeSet<String> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		set = new TreeSet<>();
		
		for(int i = 0; i < N; i++) {
			String res = "";
			for(int j = 0; j < M; j++) {
				if(board[i][j] == '#') {
					if(res.length() >= 2) {
						set.add(res);
					}
					res = "";
					continue;
				}
				
				res += board[i][j];
			}
			
			if(res.length() >= 2) {
				set.add(res);
			}
		}
		
		for(int i = 0; i < M; i++) {
			String res = "";
			for(int j = 0; j < N; j++) {
				if(board[j][i] == '#') {
					if(res.length() >= 2) {
						set.add(res);
					}
					res = "";
					continue;
				}
				
				res += board[j][i];
			}
			
			if(res.length() >= 2) {
				set.add(res);
			}
		}
		
		System.out.println(set.first());
	}
	
}