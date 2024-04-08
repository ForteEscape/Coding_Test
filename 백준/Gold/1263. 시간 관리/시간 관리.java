import java.util.*;
import java.io.*;

class Main {
	
	static class Pair implements Comparable<Pair>{
		int t;
		int end;
		
		Pair(int t, int end) {
			this.t = t;
			this.end = end;
		}
		
		@Override
		public int compareTo(Pair o){
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
			int end = Integer.parseInt(st.nextToken());
			
			pairs[i] = new Pair(t, end);
		}
		
		Arrays.sort(pairs);
		
		int start = 0;
		int end = pairs[0].end - pairs[0].t;
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
			if(time + pairs[i].t > pairs[i].end) {
				return false;
			}
			
			time = time + pairs[i].t;
		}
		
		return true;
	}
}