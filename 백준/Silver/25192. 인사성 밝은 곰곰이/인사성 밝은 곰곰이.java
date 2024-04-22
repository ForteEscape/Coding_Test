import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Set<String> set = new HashSet<>();
		int ans = 0;
		
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			
			if(data.equals("ENTER")) {
				set.clear();
			} else {
				if(!set.contains(data)) {
					set.add(data);
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
}