import java.io.*;
import java.util.*;

public class Main {
	
	static int L, C;
	static char[] candidate;
	static Set<Character> mo;
	
	static {
		mo = new HashSet<>();
		mo.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		candidate = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			candidate[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(candidate);
		
		solve(0, new StringBuilder(), L);
	}
	
	public static void solve(int selected, StringBuilder sb, int r) {
		if(r == 0) {
			if(sb.length() < 3) return;
			String result = sb.toString();
			int cnt = 0;
			
			for(int i = 0; i < result.length(); i++) {
				if(mo.contains(result.charAt(i))) {
					cnt++;
				}
			}
			
			if(cnt == 0 || result.length() - cnt < 2) {
				return;
			}
			
			System.out.println(sb.toString());
			return;
		}
		
		if(selected == C) {
			return;
		}
		
		sb.append(candidate[selected]);
		solve(selected + 1, sb, r - 1);
		sb.deleteCharAt(sb.length() - 1);
		solve(selected + 1, sb, r);
		
	}
}