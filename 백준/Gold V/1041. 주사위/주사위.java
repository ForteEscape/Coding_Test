import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] dice = new int[6];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N == 1) {
			int res = 0;
			Arrays.sort(dice);
			for(int i = 0; i < 5; i++) {
				res += dice[i];
			}
			
			System.out.println(res);
			return;
		}
		
		long[] sum = new long[3];
		
		// 3면
		for(int i = 0, idx = 5;i < 3; i++, idx--) {
			sum[0] += Math.min(dice[i], dice[idx]);
		}
		
		sum[0] *= 4;
		
		// 2면
		int max = Integer.MAX_VALUE;
		for(int i = 0; i < 6; i++) {
			for(int j = i + 1; j < 6; j++) {
				if(i + j != 5) {
					max = Math.min(max, dice[i] + dice[j]);
				}
			}
		}
		
		sum[1] = max;
		sum[1] *= (8 * (N - 2) + 4);
		
		int min = dice[0];
		for(int i = 1; i < 6; i++) {
			min = Math.min(min, dice[i]);
		}
		
		sum[2] = min;
		sum[2] *= (5L * (N - 2) * (N - 2) + 4L * (N - 2));
		
		System.out.println(sum[0] + sum[1] + sum[2]);
	}
	
}