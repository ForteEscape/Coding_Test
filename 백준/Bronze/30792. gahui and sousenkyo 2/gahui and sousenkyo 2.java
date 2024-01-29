import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int vote = Integer.parseInt(br.readLine());
		
		Integer[] characterVotes = new Integer[N - 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N - 1; i++) {
			characterVotes[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(characterVotes, (o1, o2) -> o2 - o1);
		
		int ans = 0;
		for(int i = 0; i < N - 1; i++) {
			if(characterVotes[i] < vote) {
				ans = i + 1;
				break;
			}
		}
		
		if(ans == 0) {
			ans = N;
		}
		
		System.out.println(ans);
	}
}
