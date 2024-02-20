import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String data = br.readLine();
		
		List<String> changeData = new ArrayList<>();
		Set<String> changeDataSet = new HashSet<>();
		
		while(true) {
			StringBuilder sb = new StringBuilder();
			Deque<Character> stack = new ArrayDeque<>();
			
			for(int i = 0; i < data.length(); i++) {
				if(i % 2 != 0) {
					if(i + 1 < data.length()) {
						stack.addLast(data.charAt(i));
						continue;
					}
				} 
				
				sb.append(data.charAt(i));
			}
			
			while(!stack.isEmpty()) {
				sb.append(stack.pollLast());
			}
			
			if(changeDataSet.contains(sb.toString())) {
				break;
			}
			
			String result = sb.toString();
			changeDataSet.add(result);
			changeData.add(result);
			data = result;
		}
		
		int idx = (N % changeData.size()) - 1 < 0 ? changeData.size() - 1 : (N % changeData.size()) - 1;
		System.out.println(changeData.get(idx));
	}
}