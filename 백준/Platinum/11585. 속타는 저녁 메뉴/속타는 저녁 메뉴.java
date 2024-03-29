import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static String pattern;
	static String all;
	static int[] pi;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(st.nextToken());
		}
		
		pattern = sb.toString();
		
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(st.nextToken());
		}
		
		String data = sb.toString();
		cnt = 0;
		pi = new int[pattern.length()];
		
		getPi();
		all = data + data;
		
		kmp();
		
		int res = getGCD(cnt, N);
		
		System.out.println(cnt / res + "/" + N / res);
	}
	
	static int getGCD(int n, int m) {
		if(m == 0) {
			return n;
		}
		
		return getGCD(m, n % m);
	}
	
	static void getPi() {
		int j = 0;
		
		for(int i = 1; i < pattern.length(); i++) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
	}
	
	static void kmp() {
		int j = 0;
		
		for(int i = 0; i < all.length() - 1; i++) {
			while(j > 0 && all.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			if(all.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length() - 1) {
					cnt++;
					j = pi[j];
				} else {
					++j;
				}
			}
		}
	}
}