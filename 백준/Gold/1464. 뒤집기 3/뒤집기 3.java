import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();
		
		int N = data.length();
		
		String temp = data.substring(0, 1);
		
		for(int i = 1; i < N; i++) {
			if(temp.charAt(i - 1) < data.charAt(i)) {
				temp = data.charAt(i) + temp;
			} else {
				temp = temp + data.charAt(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = temp.length() - 1; i >= 0; i--) {
			sb.append(temp.charAt(i));
		}
		System.out.println(sb);
	}
}