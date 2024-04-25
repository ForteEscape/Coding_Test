import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();
		
		int[] cnt = {0, 0};
		
		for(int i = 0; i < data.length(); i++) {
			cnt[data.charAt(i) - '0']++;
		}
		
		cnt[0] /= 2;
		cnt[1] /= 2;
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < cnt[0]; i++) {
			sb.append(0);
		}
		
		for(int j = 0; j < cnt[1]; j++) {
			sb.append(1);
		}
		
		System.out.println(sb);
	}
 }