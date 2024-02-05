import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> queue = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			queue.addLast(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int idx = 1;
		while(!queue.isEmpty()) {
			if(idx % K == 0) {
				if(queue.size() == 1) {
					sb.append(queue.pollFirst());
				} else {
					sb.append(queue.pollFirst()).append(", ");
				}
			} else {
				queue.addLast(queue.pollFirst());
			}
			
			idx++;
		}
		sb.append(">");
		System.out.println(sb);
	}
}