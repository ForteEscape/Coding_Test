import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] data = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		for(int i = 0; i < M; i++) {
			sum += data[i];
		}
		
		int tmp = sum;
		
		int start = 0;
		int end = M - 1;
		int ans = sum;
		while(end < N - 1) {
			sum -= data[start++];
			sum += data[++end];
			
			ans = Math.max(ans, sum);
		}
		
		if(ans == 0) {
			System.out.println("SAD");
			return;
		}
		
		start = 0;
		end = M - 1;
		sum = tmp;
		
		int cnt = sum == ans ? 1 : 0;
		
		while(end < N - 1) {
			sum -= data[start++];
			sum += data[++end];
			
			if(sum == ans) {
				cnt++;
			}
		}
		
		System.out.println(ans);
		System.out.println(cnt);
	}
}