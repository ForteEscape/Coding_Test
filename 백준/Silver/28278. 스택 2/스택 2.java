import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		
		Deque<Integer> stack = new ArrayDeque<>();
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int query = Integer.parseInt(st.nextToken());
			
			if(query == 1) {
				stack.addLast(Integer.parseInt(st.nextToken()));
			} else if(query == 2) {
				sb.append(stack.isEmpty() ? -1 : stack.pollLast()).append("\n");
			} else if(query == 3) {
				sb.append(stack.size()).append("\n");
			} else if(query == 4) {
				sb.append(stack.isEmpty() ? 1 : 0).append("\n");
			} else {
				sb.append(stack.isEmpty() ? -1 : stack.peekLast()).append("\n");
			}
		}
		
		System.out.print(sb);
	}
}