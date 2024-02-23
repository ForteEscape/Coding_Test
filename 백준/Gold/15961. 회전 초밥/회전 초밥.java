import java.io.*;
import java.util.*;

public class Main {
	
	static int N, D, K, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[] data = new int[N];
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		Map<Integer, Integer> sushiType = new HashMap<>();
		Deque<Integer> sushiLine = new ArrayDeque<>();
		
		int ans = -1;
		for(int i = 0; i < N; i++) {
			if(i == 0) {
				for(int j = 0; j < K; j++) {
					sushiLine.addLast(data[j]);
					sushiType.put(data[j], sushiType.getOrDefault(data[j], 0) + 1);
				}
				
				int result = sushiType.keySet().size();
				if(!sushiType.containsKey(C)) {
					result++;
				}
				
				ans = Math.max(ans, result);

				continue;
			}
			
			int sushi = sushiLine.pollFirst();
			sushiType.put(sushi, sushiType.get(sushi) - 1);
			if(sushiType.get(sushi) == 0) {
				sushiType.remove(sushi);
			}
			
			sushiLine.addLast(data[(i + K - 1) % N]);
			sushiType.put(data[(i + K - 1) % N], sushiType.getOrDefault(data[(i + K - 1) % N], 0) + 1);
			
			int result = sushiType.keySet().size();
			if(!sushiType.containsKey(C)) {
				result++;
			}
			
			ans = Math.max(ans, result);
		}
		
		System.out.println(ans);
	}
}