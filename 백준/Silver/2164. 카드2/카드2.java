import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> queue = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			queue.addLast(i);
		}
		
		while(queue.size() != 1) {
			queue.pollFirst();
			queue.addLast(queue.pollFirst());
		}
		
		System.out.println(queue.peek());
	}
}