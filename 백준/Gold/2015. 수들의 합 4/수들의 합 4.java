import java.util.*;
import java.io.*;

public class Main {
	
	static long[] arr;
	static int N, K;
	static Map<Long, Integer> map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N];
		map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			arr[i] =  arr[i - 1] + tmp;
		}
		
		long ans = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == K) ans++;
			
			if(!map.containsKey(arr[i])) {
				map.put(arr[i], 0);
			}
			ans += map.get(arr[i] - K) == null ? 0 : map.get(arr[i] - K);
			map.put(arr[i], map.get(arr[i]) + 1);
		}
		
		System.out.println(ans);
	}
}