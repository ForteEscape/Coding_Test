import java.util.*;
import java.io.*;

public class Main {
	
	static Set<Integer> set;
	static int N, S;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < S; i++) {
			int element = Integer.parseInt(st.nextToken());
			set.add(element);
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int i = 1; i <= 1001; i++) {
			if(set.contains(i)) {
				continue;
			}
			
			for(int j = i; j <= 1001; j++) {
				if(set.contains(j)) {
					continue;
				}
				
				for(int k = j; k <= 1001; k++) {
					if(set.contains(k)) {
						continue;
					}
					
					ans = Math.min(ans, Math.abs(N - (i * j * k)));
					if(N < i * j * k) {
						break;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
 }