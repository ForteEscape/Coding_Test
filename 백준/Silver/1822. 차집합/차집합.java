import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		TreeSet<Integer> set = new TreeSet();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int element = Integer.parseInt(st.nextToken());
			if(set.contains(element)) {
				set.remove(element);
			}
		}
		
		if(set.size() == 0) {
			System.out.println(0);
		} else {
			StringBuilder sb = new StringBuilder();
			for(int element : set) {
				sb.append(element).append(" ");
			}
			
			System.out.println(set.size());
			System.out.println(sb);
		}
	}
}
