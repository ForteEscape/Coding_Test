import java.util.*;
import java.io.*;

public class Main {
	
	static class Pair {
		int height;
		int cnt;
		
		Pair(int height, int cnt) {
			this.height = height;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		long ans = 0;
		
		Deque<Pair> stack = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			Pair newPair = new Pair(height, 1);
			
			while(!stack.isEmpty() && stack.peekLast().height <= height) {
				Pair pair = stack.pollLast();
				
				ans += pair.cnt;
				if(pair.height == height) {
					newPair.cnt += pair.cnt;
				}
			}
			
			if(!stack.isEmpty()) {
				ans++;
			}
			
			stack.addLast(newPair);
		}
		
		System.out.println(ans);
	}
}