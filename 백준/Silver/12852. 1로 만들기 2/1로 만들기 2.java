import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static final int MAX_LENGTH = 1000001;
	static final int INF = Integer.MAX_VALUE;
	static int[][] dp = new int[MAX_LENGTH][2];
	static List<Integer> ans = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dp[1][0] = 0;
		dp[1][1] = 1;
		for(int i = 2; i <= N; i++) {
			int minus = dp[i - 1][0] + 1;
			int divideTwo = INF;
			int divideThree = INF;
			
			if(i % 2 == 0) {
				divideTwo = dp[i / 2][0] + 1;
			}
			
			if(i % 3 == 0) {
				divideThree = dp[i / 3][0] + 1;
			}
			
			if(minus <= divideTwo && minus <= divideThree) {
				dp[i][0] = minus;
				dp[i][1] = i - 1;
			} else if(divideTwo <= minus && divideTwo <= divideThree) {
				dp[i][0] = divideTwo;
				dp[i][1] = i / 2;
			} else if(divideThree <= minus && divideThree <= divideTwo) {
				dp[i][0] = divideThree;
				dp[i][1] = i / 3;
			}
			
			dp[i][0] = Math.min(minus, Math.min(divideTwo, divideThree));
		}
		
		int idx = N;
		ans.add(N);
		while(idx != 1) {
			ans.add(dp[idx][1]);
			
			idx = dp[idx][1];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(dp[N][0]).append("\n");
		for(int element: ans) {
			sb.append(element).append(" ");
		}
		System.out.println(sb);
	}

	
}