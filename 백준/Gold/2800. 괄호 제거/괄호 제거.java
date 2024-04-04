import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
	
	static class BracketInfo {
		int start;
		int end;
		
		BracketInfo(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	static class Bracket {
		char val;
		int idx;
		
		Bracket(char val, int idx) {
			this.val = val;
			this.idx = idx;
		}
	}
	
	static List<BracketInfo> bracketInfo;
	static Set<String> ansSet;
	static String data;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		data = br.readLine();
		
		Deque<Bracket> stack = new ArrayDeque<>();
		bracketInfo = new ArrayList<>();
		ansSet = new TreeSet<>();
		
		for(int i = 0; i < data.length(); i++) {
			if(data.charAt(i) == '(') {
				stack.addLast(new Bracket('(', i));
			}
			
			if(data.charAt(i) == ')') {
				Bracket match = stack.pollLast();
				bracketInfo.add(new BracketInfo(match.idx, i));
			}
		}
		
		solve(0, 0, bracketInfo.size(), new ArrayList<>());
		
		for(String element: ansSet) {
			System.out.println(element);
		}
	}
	
	static void solve(int cur, int idx, int size, List<Integer> cand) {
		if(idx == size) {
			if(cand.size() == 0) {
				return;
			}
			
			Set<Integer> skipSet = new HashSet<>();
			for(int element : cand) {
				skipSet.add(bracketInfo.get(element).start);
				skipSet.add(bracketInfo.get(element).end);
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < data.length(); i++) {
				if(skipSet.contains(i)) {
					continue;
				}
				
				sb.append(data.charAt(i));
			}
			
			ansSet.add(sb.toString());
			return;
		}
		
		cand.add(idx);
		solve(cur + 1, idx + 1, size, cand);
		
		cand.remove(cand.size() - 1);
		solve(cur, idx + 1, size, cand);
	}
}