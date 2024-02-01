import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 0; test_case < T; test_case++) {
			int K = Integer.parseInt(br.readLine());
			
			PriorityQueue<Long> pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) {
				pq.offer(Long.parseLong(st.nextToken()));
			}
			
			long minCost = 0;
			while(pq.size() != 1) {
				long f1 = pq.poll();
				long f2 = pq.poll();
				
				pq.offer(f1 + f2);
				
				minCost += (f1 + f2);
			}
			
			System.out.println(minCost);
		}
	}

}