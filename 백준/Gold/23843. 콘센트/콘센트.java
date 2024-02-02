import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> pq2 = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}
		
		List<Integer> list = new ArrayList<>();
		int ans = 0;
		int idx = 0;
		loop1 : while(!pq.isEmpty()) {
			while(!pq.isEmpty() && pq2.size() < M) {
				pq2.offer(pq.poll());
			}
			
			list = new ArrayList<>();
			int last = pq2.peek();
			while(!pq2.isEmpty()) {
				int element = pq2.poll();
				
				if(element - last > 0) {
					list.add(element - last);
				}
			}
			
			pq2 = new PriorityQueue<>(list);
			ans += last;
		}
		
		int last = 0;
		while(!pq2.isEmpty()) {
			last = pq2.poll();
		}
		
		System.out.println(ans + last);
	}
}