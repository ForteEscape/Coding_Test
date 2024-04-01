import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class UseTime {
		int start;
		int end;
		int idx;
		
		UseTime(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "UseTime [start=" + start + ", end=" + end + ", idx=" + idx + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		UseTime[] times = new UseTime[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			times[i] = new UseTime(start, end);
		}
		
		Arrays.sort(times, (u1, u2) -> u1.start - u2.start);
		
		int[] cnt = new int[100001];
		PriorityQueue<UseTime> pq = new PriorityQueue<>((u1, u2) -> u1.end - u2.end);
		PriorityQueue<Integer> emptySpace = new PriorityQueue<>();
		
		for(int i = 1; i <= 100000; i++) {
			emptySpace.offer(i);
		}
		
		for(UseTime element : times) {
			while(!pq.isEmpty() && pq.peek().end < element.start) {
				UseTime tmp = pq.poll();
				emptySpace.offer(tmp.idx);
			}
			
			int idx = emptySpace.poll();
			cnt[idx]++;
			
			element.idx = idx;
			pq.offer(element);
		}
		
		int ans = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= 100000; i++) {
			if(cnt[i] > 0) {
				ans++;
				sb.append(cnt[i] + " ");
			}
		}
		System.out.println(ans);
		System.out.println(sb);
	}
}