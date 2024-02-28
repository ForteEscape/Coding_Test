import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		TreeSet<String> existsSet = new TreeSet<>((s1, s2) -> s2.compareTo(s1));
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String name = st.nextToken();
			String query = st.nextToken();
			
			if(query.equals("enter")) {
				existsSet.add(name);
			} else if(query.equals("leave")) {
				if(!existsSet.isEmpty())
					existsSet.remove(name);
			}
		}
		
		for(String name : existsSet) {
			System.out.println(name);
		}
	}
}