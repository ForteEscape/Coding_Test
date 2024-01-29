import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int pivot = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		for(int i = 0; i < 4; i++) {
			int element = Integer.parseInt(st.nextToken());
			
			if(pivot - element <= 1000) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
