import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] v = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			dp[i] = v[i];
			for(int j = 1; j < i; j++) {
				if(v[i] > v[j]) {
					dp[i] = Math.max(dp[i], dp[j] + v[i]);
				}
			}
		}
		
		int ans = -1;
		for(int i = 1; i <= N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}

	
}