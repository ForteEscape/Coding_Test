import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String S = br.readLine();
		Deque<Character> stack = new ArrayDeque<>();
		int days = 0;
		int maxDays = -1;

		for (char element : S.toCharArray()) {
			if (stack.isEmpty()) {
				stack.addLast(element);
				days += 1;

				maxDays = Math.max(maxDays, days);
				continue;
			}

			if (element == '(') {
				if(stack.peekLast() == ')') {
					stack.pollLast();
					days -= 1;
				} else {
					stack.addLast(element);
					days += 1;
				}
			} else { // element == ')'
				if(stack.peekLast() == '(') {
					stack.pollLast();
					days -= 1;
				} else {
					stack.addLast(element);
					days += 1;
				}
			}
			
			maxDays = Math.max(maxDays, days);
		}
		
		if(!stack.isEmpty()) {
			System.out.println(-1);
		} else {
			System.out.println(maxDays);
		}
	}

}