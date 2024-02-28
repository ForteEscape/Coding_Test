import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());	
		
		int[] velocity = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			velocity[i] = Integer.parseInt(st.nextToken());
		}
		
		long pre = 0;
		long ans =  0;
		for(int i = N - 1; i >= 0; i--) {
			if(pre < velocity[i]) {
				pre++;
			} else {
				pre = velocity[i];
			}
			
			ans += pre;
		}
		
		System.out.println(ans);
	}
}