import java.util.*;
import java.io.*;

public class Main {
	
	static Set<Integer> set;
	static int N, S;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		StringBuilder sb = new StringBuilder();

		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			
			String sub = st.nextToken();
			String main = st.nextToken();
			
			int idx = 0;
			
			for(int i = 0; i < main.length(); i++) {
				if(main.charAt(i) == sub.charAt(idx)) {
					idx++;
				}
				
				if(idx >= sub.length()) {
					break;
				}
			}
			
			if(idx == sub.length()) {
				sb.append("Yes");
			} else {
				sb.append("No");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
 }