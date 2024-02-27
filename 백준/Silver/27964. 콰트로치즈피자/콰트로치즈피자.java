import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	
		
		Set<String> cheeseSet = new HashSet<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			String toping = st.nextToken();
			
			if(toping.length() < 6) {
				continue;
			}
			
			String cheesePart = toping.substring(toping.length() - 6);
			
			if("Cheese".equals(cheesePart)) {
				cheeseSet.add(toping);
			}
		}
		
		System.out.println(cheeseSet.size() >= 4 ? "yummy" : "sad");
	}
	
}