import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String res = "";
		
		while((res = br.readLine()) != null && !res.isEmpty()) {
			int N = Integer.parseInt(res);
			int ans = 1;
			
			int tgt = 1;
			while(true) {
				if(tgt % N == 0) {
					System.out.println(ans);
					break;
				}
				
				tgt = tgt * 10 + 1;
				tgt %= N;
				
				ans++;
			}
		}
	}
}
