import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			List<Long> fibo = new ArrayList<>();
			int N = Integer.parseInt(br.readLine());
			
			fibo.addAll(Arrays.asList(1L, 1L));
			int index = 2;
			while(true) {
				long result = fibo.get(index - 2) + fibo.get(index - 1);
				
				if(result > N) {
					break;
				}
				fibo.add(result);
				index++;
			}
			
			fibo.sort(Comparator.comparingLong(e -> -e));
			Deque<Long> stack = new ArrayDeque<>();
			while(N > 0) {
				for(int i = 0; i < fibo.size(); i++) {
					if(fibo.get(i) <= N) {
						N -= fibo.get(i);
						stack.addLast(fibo.get(i));
						break;
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty()) {
				sb.append(stack.pollLast()).append(" ");
			}
			System.out.println(sb.toString());
		}
		
		
	}
	
}