import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static Map<Integer, ArrayList<Integer>> mapY;
	static Map<Integer, ArrayList<Integer>> mapX;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		mapY = new HashMap<>();
		mapX = new HashMap<>();
		
		for(int t = 0; t < T; t++) {
			mapY.clear();
			mapX.clear();
			
			int N = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if(!mapX.containsKey(x)) {
					mapX.put(x, new ArrayList<>());
				}
				
				mapX.get(x).add(y);
				
				if(!mapY.containsKey(y)) {
					mapY.put(y, new ArrayList<>());
				}
				
				mapY.get(y).add(x);
			}
			
			boolean flag = true;
			for(int element: mapY.keySet()) {
				if(mapY.get(element).size() != mapX.keySet().size()) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				System.out.println("BALANCED");
			} else {
				System.out.println("NOT BALANCED");
			}
		}
	}
}