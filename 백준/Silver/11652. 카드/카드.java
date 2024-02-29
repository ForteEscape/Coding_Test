import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Map<Long, Integer> map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			Long num = Long.parseLong(br.readLine());
			
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		long minKey = Long.MAX_VALUE;
		int cnt = Integer.MIN_VALUE;
		for(long key : map.keySet()) {
			if(cnt <= map.get(key)) {
				minKey = cnt == map.get(key) ? Math.min(key, minKey) : key;
				cnt = map.get(key);
			}
		}
		
		System.out.println(minKey);
	}
}