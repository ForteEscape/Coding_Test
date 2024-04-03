import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static long[] data;
	static Map<Long, Boolean> map;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		data = new long[N];
		map = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			data[i] = Long.parseLong(st.nextToken());
			map.put(data[i], false);
		}
		
		for(int i = 0; i < N; i++) {
			ArrayList<Long> ansList = new ArrayList<>();
			ansList.add(data[i]);
			solve(data[i], 0, ansList);
		}
	}
	
	static void solve(long start, int cur, ArrayList<Long> ansList) {
		if(cur == N - 1) {
			StringBuilder sb = new StringBuilder();
			for(long element : ansList) {
				sb.append(element).append(" ");
			}
			
			System.out.println(sb);
			System.exit(0);
		}
		
		if(map.containsKey(start * 2) && !map.get(start * 2)) {
			map.put(start * 2, true);
			ansList.add(start * 2);
			solve(start * 2, cur + 1, ansList);
			ansList.remove(ansList.size() - 1);
			map.put(start * 2, false);
		}
		
		if(start % 3 == 0) {
			if(map.containsKey(start / 3) && !map.get(start / 3)) {
				map.put(start / 3, true);
				ansList.add(start / 3);
				solve(start / 3, cur + 1, ansList);
				ansList.remove(ansList.size() - 1);
				map.put(start / 3, false);
			}
		}
	}
	
}