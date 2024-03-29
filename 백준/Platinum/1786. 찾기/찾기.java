import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static String pattern;
	static String all;
	static int[] pi;
	static List<Integer> answer;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		all = br.readLine();
		pattern = br.readLine();
		
		pi = new int[pattern.length()];
		cnt = 0;
		answer = new ArrayList<>();
		getPi();
		kmp();
		
		System.out.println(cnt);
		StringBuilder sb = new StringBuilder();
		for(int element: answer) {
			sb.append(element).append(" ");
		}
		System.out.println(sb);
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
		
		for(int i = 0; i < all.length(); i++) {
			while(j > 0 && all.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			if(all.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length() - 1) {
					answer.add(i - (pattern.length() - 1) + 1);
					cnt++;
					j = pi[j];
				} else {
					j++;
				}
			}
		}
	}
}