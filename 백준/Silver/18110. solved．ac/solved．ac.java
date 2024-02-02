import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] data = new long[N];
		for(int i = 0; i < N; i++) {
			data[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(data);
		
		long cut = Math.round((double)N * 0.15);
		long ans = 0L;
		
		int cnt = 0;
		for(long i = cut; i < N - cut; i++) {
			ans += data[(int)i];
			cnt++;
		}
		
		ans = Math.round((double)ans / (double)cnt);
		System.out.println(ans);
	}
}