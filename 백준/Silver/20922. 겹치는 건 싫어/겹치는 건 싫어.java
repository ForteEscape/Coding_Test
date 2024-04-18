import java.util.*;
import java.io.*;

public class Main {

	static int N, K;
	static int[] data;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		data = new int[N];
		
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		
		int start = 0;
		int end = 1;
		int cnt = 1;
		
		map.put(data[0], 1);
		int ans = 0;
		
		while(end < N) {
			if(!map.containsKey(data[end])) {
				map.put(data[end], 1);
				end++;
				cnt++;
			} else {
				if(map.get(data[end]) + 1 > K) {
					map.put(data[start], map.get(data[start]) - 1);
					start++;
					cnt--;
				} else {
					map.put(data[end], map.get(data[end]) + 1);
					end++;
					cnt++;
				}
			}
			
			ans = Math.max(ans, cnt);
		}
		
		System.out.println(ans);
	}

}