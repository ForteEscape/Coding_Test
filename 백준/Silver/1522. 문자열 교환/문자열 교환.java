import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();
		
		// a의 개수만큼의 범위를 가지는 sliding window 내부에서 존재하는 B의 count가 해당 범위에서의 교환 개수이다.
		int[] cnt = new int[2];
		int[] windowCnt = new int[2];
		
		for(char element : data.toCharArray()) {
			cnt[element - 'a']++;
		}
		
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < data.length(); i++) {
			if(i == 0) {
				for(int j = 0; j < cnt[0]; j++) {
					windowCnt[data.charAt(j) - 'a']++;
				}
			} else {
				windowCnt[data.charAt(i - 1) - 'a']--;
				windowCnt[data.charAt((i + cnt[0] - 1) % data.length()) - 'a']++;
			}
			
			ans = Math.min(ans, windowCnt[1]);
		}
		
		System.out.println(ans);
	}
}