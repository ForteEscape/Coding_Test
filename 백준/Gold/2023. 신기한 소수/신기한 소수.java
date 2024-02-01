import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static char[] data = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		solve(0, N, sb);
	}
	
	public static void solve(int cur, int N, StringBuilder current) {
		if(cur > 0) {
			int element = Integer.parseInt(current.toString());
			
			if(element == 1) {
				return;
			}
			
			for(int i = 2; i <= Math.sqrt(element); i++) {
				if(element % i == 0) {
					return;
				}
			}
		}
		
		if(cur == N) {
			if(Integer.parseInt(current.toString()) < Math.pow(10, N - 1)) {
				return;
			}
			System.out.println(current);
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			sb.append(data[i]);
			solve(cur + 1, N, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}