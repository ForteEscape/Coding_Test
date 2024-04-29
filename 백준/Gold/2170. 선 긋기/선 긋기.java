import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] line;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		line = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			line[i] = new int[] {start, end};
		}
		
		Arrays.sort(line, (o1, o2) -> o1[0] - o2[0] == 0 ? o2[1] - o1[1] : o1[0] - o2[0]);
		
		int start = line[0][0];
		int end = line[0][1];
		
		long ans = 0;
		
		int prevStart = 0;
		int prevEnd = 0;
		
		for(int i = 1; i < N; i++) {
			if(line[i][0] <= end) {
				end = Math.max(end, line[i][1]);
			} else {
				ans += (end - start);
				
				prevStart = start;
				prevEnd = end;
				
				start = line[i][0];
				end = line[i][1];
			}
		}
		
		if(start != prevStart || end != prevEnd) {
			ans += end - start;
		}
		
		System.out.println(ans);
	}
}