import java.util.*;
import java.io.*;

public class Main {
	
	static Set<String> set;
	
	static {
		set = new HashSet<>();
		set.addAll(Arrays.asList("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();
		
		int idx = 0;
		int cnt = 0;
		while(idx < data.length()) {
			if(idx + 1 < data.length()) {
				String res = "";
				for(int i = idx; i <= idx + 1; i++) {
					res += data.charAt(i);
				}
				
				if(set.contains(res)) {
					cnt++;
					idx += 2;
					continue;
				}
			}
			
			if(idx + 2 < data.length()) {
				String res = "";
				for(int i = idx; i <= idx + 2; i++) {
					res += data.charAt(i);
				}
				
				if(set.contains(res)) {
					cnt++;
					idx += 3;
					continue;
				}
			}
			
			cnt++;
			idx++;
		}
		
		System.out.println(cnt);
	}
}