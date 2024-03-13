import java.util.*;
import java.io.*;

public class Main {
	
	static int[] cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		cnt = new int[10001];
		
		for(int i = 0; i < N; i++) {
			int data = Integer.parseInt(br.readLine());
			
			cnt[data]++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < 10001; i++) {
			for(int j = 0; j < cnt[i]; j++) {
				sb.append(i).append("\n");
			}
		}
		
		System.out.print(sb);
	}
}