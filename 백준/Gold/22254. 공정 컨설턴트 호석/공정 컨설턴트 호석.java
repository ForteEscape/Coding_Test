import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Machine implements Comparable<Machine>{
		long endTime;
		
		Machine(long endTime){
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Machine o) {
			if(this.endTime < o.endTime) {
				return -1;
			} else {
				return 1;
			}
		}
	}
	
	static int[] time;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		time = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 1;
		int right = N;
		int ans = Integer.MAX_VALUE;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(chk(mid)) {
				ans = Math.min(ans, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(ans);
	}
	
	public static boolean chk(int cnt) {
		PriorityQueue<Machine> pq = new PriorityQueue<>();
		
		for(int i = 0; i < cnt; i++) {
			pq.offer(new Machine(0));
		}
		
		for(int i = 0; i < N; i++) {
			Machine target = pq.poll();
			target.endTime += time[i];
			
			if(target.endTime > M) {
				return false;
			}
			
			pq.offer(target);
		}
		
		return true;
	}
}