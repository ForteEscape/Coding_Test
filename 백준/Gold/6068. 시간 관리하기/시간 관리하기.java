import java.util.*;
import java.io.*;

class Main {
	
	static class Pair implements Comparable<Pair>{
		int start;
		int end;
		
		Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(Pair o) {
			return Integer.compare(this.end, o.end);
		}
	}
	
	static Pair[] pairs;
	static int N;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		pairs = new Pair[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int deadline = Integer.parseInt(st.nextToken());
			
			pairs[i] = new Pair(t, deadline);
		}
		
		Arrays.sort(pairs);
		
		int start = 0;
		int end = pairs[0].end - pairs[0].start;
		int ans = -1;
		
		while(start <= end) {
			int mid = (start + end) >> 1;
		
			if(chk(mid)) {
				ans = Math.max(ans, mid);
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean chk(int mid) {
		int time = mid;
		
		for(int i = 0; i < N; i++) {
			if(time + pairs[i].start > pairs[i].end) {
				return false;
			}
			
			time = time + pairs[i].start;
		}
		
		return true;
	}
}