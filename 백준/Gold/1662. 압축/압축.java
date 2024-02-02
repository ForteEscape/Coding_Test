import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		Deque<Integer> lenStack = new ArrayDeque<>();
		Deque<Integer> mulStack = new ArrayDeque<>();
		
		int cnt = 0;
		
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '(') {
				cnt -= 1;
				int mulNum = line.charAt(i - 1) - '0';
				lenStack.addLast(cnt);
				mulStack.addLast(mulNum);
				cnt = 0;
			} else if(line.charAt(i) == ')') {
				int val = mulStack.pollLast();
				
				val *= cnt;
				int plus = lenStack.pollLast();
				cnt = plus + val;
			} else {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}