import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static final int MAX = 1000_000_001;
	static class Tower {
		int height;
		int idx;
		
		Tower(int height, int idx) {
			this.height = height;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Tower> stack = new ArrayDeque<>();
		stack.addLast(new Tower(MAX, 0));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			int element = Integer.parseInt(st.nextToken());
			
			while(stack.peekLast().height < element) {
				stack.pollLast();
			}
			
			sb.append(stack.peekLast().idx).append(" ");
			stack.addLast(new Tower(element, i));
		}
		
		System.out.println(sb);
	}
}