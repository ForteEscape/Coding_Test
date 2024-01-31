import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] v = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = -1;
		int[] dp = new int[N];
		for(int i = 0; i < N; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(v[j] < v[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			
			int[] temp = new int[N - i - 1];
			
			for(int j = N - 1, idx = 0; j > i; j--, idx++) {
				temp[idx] = 1;
				for(int k = N - 1, idx2 = 0; k > j; k--, idx2++) {
					if(v[k] < v[j]) {
						temp[idx] = Math.max(temp[idx], temp[idx2] + 1);
					}
				}
			}
			
			int back = 0;
			for(int j = 0, idx = N - 1; j < temp.length; j++, idx--) {
				if(v[idx] < v[i]) {
					back = Math.max(back, temp[j]);
				}
			}
			
			ans = Math.max(ans, dp[i] + back);
		}
		
		for(int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}

	
}