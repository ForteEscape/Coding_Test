import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> queue = new ArrayDeque<>();
		
		while(true) {
			int element = Integer.parseInt(br.readLine());
			
			if(element == -1) {
				break;
			}
			
			if(element > 0) {
				if(queue.size() < N) {
					queue.addLast(element);
				}
			} else if(element == 0) {
				queue.pollFirst();
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(queue.isEmpty()) {
			sb.append("empty");
		}
		
		while(!queue.isEmpty()) {
			sb.append(queue.pollFirst()).append(" ");
		}
		
		System.out.println(sb);
	}
}