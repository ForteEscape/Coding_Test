import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String L = st.nextToken();
		String R = st.nextToken();
		
		int ans = L.length();
		
		for(int i = 0; i < R.length() - L.length() + 1; i++) {
			int temp = 0;
			
			for(int j = 0; j < L.length(); j++) {
				if(L.charAt(j) != R.charAt(i + j)) {
					temp++;
				}
			}
			
			ans = Math.min(ans, temp);
		}
		
		System.out.println(ans);
	}
}
