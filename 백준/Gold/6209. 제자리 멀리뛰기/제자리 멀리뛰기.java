import java.util.*;
import java.io.*;

public class Main {
	
	static int[] land;
	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		land = new int[M + 2];
		for(int i = 1; i <= M; i++) {
			land[i] = Integer.parseInt(br.readLine());
		}
		
		land[M + 1] = N;
		Arrays.sort(land);
		
		int start = 0;
		int end = N;
		int ans = -1;
		
		while(start <= end) {
			int now = 0;
			int mid = (start + end) / 2;
			int cnt = 0;
			
			for(int i = 1; i < land.length; i++) {
				if(mid + land[now] > land[i]) {
					cnt++;
				} else {
					now = i;	
				}
			}
			
			if(cnt > K) {
				end = mid - 1;
			} else {
				ans = mid;
				start = mid + 1;
			}
		}
		
		System.out.println(ans);
	}
}