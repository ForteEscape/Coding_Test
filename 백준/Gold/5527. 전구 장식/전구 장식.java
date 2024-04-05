import java.util.*;
import java.io.*;

class Main {

	static int[] data;
	static ArrayList<Integer> list;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		data = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList<>();
		
		int cnt = 1;
		for(int i = 1; i < N; i++) {
			if(data[i - 1] != data[i]) {
				cnt++;
			} else {
				list.add(cnt);
				cnt = 1;
			}
		}
		
		list.add(cnt);
		
		if(list.size() <= 2) {
			int ans = 0;
			for(int i = 0; i < list.size(); i++) {
				ans += list.get(i);
			}
			
			System.out.println(ans);
		} else {
			int start = 0;
			int end = 2;
			int sum = 0;
			
			for(int i = 0; i < 3; i++) {
				sum += list.get(i);
			}
			
			int ans = sum;
			
			while(end < list.size() - 1) {
				sum += list.get(++end);
				sum -= list.get(start++);
				
				ans = Math.max(ans, sum);
			}
			
			System.out.println(ans);
		}
	}
}