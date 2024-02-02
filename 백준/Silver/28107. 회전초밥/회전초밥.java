import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static final int MAX = 200_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] result = new int[N + 1];
		Deque<Integer>[] sushiQueue = new Deque[MAX + 1];
		for(int i = 1; i <= MAX; i++) {
			sushiQueue[i] = new ArrayDeque<>();
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			for(int j = 0; j < k; j++) {
				int sushiNum = Integer.parseInt(st.nextToken());
				sushiQueue[sushiNum].addLast(i);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int sushiNum = Integer.parseInt(st.nextToken());
			
			if(!sushiQueue[sushiNum].isEmpty()) {
				result[sushiQueue[sushiNum].pollFirst()]++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
	}
}