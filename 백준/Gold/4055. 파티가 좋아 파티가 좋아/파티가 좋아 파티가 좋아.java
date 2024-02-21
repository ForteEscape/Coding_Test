import java.io.*;
import java.util.*;

public class Main {
	
	static class Party {
		int start;
		int end;
		
		Party(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Party [start=" + start + ", end=" + end + "]";
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int day = 1;;day++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] visited;
			
			if(N == 0) {
				break;
			}
			
			Party[] parties = new Party[N];
			visited = new boolean[N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				parties[i] = new Party(start, end);
			}
			
			Arrays.sort(parties, (o1, o2) -> o1.end - o2.end == 0 ? o2.start - o1.start : o1.end - o2.end);
			
			int ans = 0;
			
			for(int t = 8; t <= 23; t++) {
				for(int d = 0; d < 2; d++) {
					for(int i = 0; i < N; i++) {
						if(parties[i].start <= t && parties[i].end > t && !visited[i]) {
							ans++;
							visited[i] = true;
							break;
						}
					}
				}
			}
			
			System.out.println("On day " + day + " Emma can attend as many as " + ans + " parties.");
		}
	}
}