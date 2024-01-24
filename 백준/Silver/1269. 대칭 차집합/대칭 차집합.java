import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<Integer> setA = new HashSet<>();
		Set<Integer> setB = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int element = Integer.parseInt(st.nextToken());
			
			setA.add(element);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int element = Integer.parseInt(st.nextToken());
			
			setB.add(element);
		}
		
		int ans = 0;
		for(int element: setA) {
			if(!setB.contains(element)) {
				ans++;
			}
		}
		
		for(int element: setB) {
			if(!setA.contains(element)) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
