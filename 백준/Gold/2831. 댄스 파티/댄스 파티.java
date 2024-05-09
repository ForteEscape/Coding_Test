import java.util.*;
import java.io.*;

public class Main {
	
	static List<Integer> manLowHeight;
	static List<Integer> manHighHeight;
	static List<Integer> womanLowHeight;
	static List<Integer> womanHighHeight;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		manLowHeight = new ArrayList<>();
		manHighHeight = new ArrayList<>();
		womanLowHeight = new ArrayList<>();
		womanHighHeight = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(height < 0) {
				manLowHeight.add(Math.abs(height));
			} else {
				manHighHeight.add(height);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(height < 0) {
				womanLowHeight.add(Math.abs(height));
			} else {
				womanHighHeight.add(height);
			}
		}
		
		Collections.sort(manLowHeight, Collections.reverseOrder());
		Collections.sort(manHighHeight, Collections.reverseOrder());
		Collections.sort(womanLowHeight, Collections.reverseOrder());
		Collections.sort(womanHighHeight, Collections.reverseOrder());
		
		System.out.println(match(womanLowHeight, manHighHeight) + match(manLowHeight, womanHighHeight));
	}
	
	static int match(List<Integer> lowHeight, List<Integer> highHeight) {
		int l = 0;
		int h = 0;
		
		int cnt = 0;
		
		while(l < lowHeight.size() && h < highHeight.size()) {
			if(lowHeight.get(l) > highHeight.get(h)) {
				cnt++;
				l++;
				h++;
			} else {
				h++;
			}
		}
		
		return cnt;
	}
}