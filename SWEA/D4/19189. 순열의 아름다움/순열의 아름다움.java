import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			long P = Integer.parseInt(st.nextToken());
			
			long sum = 0;
			long[] facto = new long[N + 1];
			facto[1] = 1;
			
			for(int i = 2; i <= N; i++) {
				facto[i] = (facto[i - 1] * i) % P;
			}
			
			for(int i = N - 1; i >= 0; i--) {
				sum += (((facto[N - i] * (N - i)) % P) * facto[i + 1]) % P;
				sum %= P;
			}
			
			System.out.println("#" + test_case + " " + sum);
		}
	}
}
