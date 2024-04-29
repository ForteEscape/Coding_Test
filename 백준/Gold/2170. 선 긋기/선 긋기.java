import java.util.*;
import java.io.*;

public class Main {
	
	static class Line implements Comparable<Line>{
		int start;
		int end;
		
		Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line o) {
			// TODO Auto-generated method stub
			return this.start == o.start ? o.end - this.end : this.start - o.start;
		}
	}
	
	static Line[] line;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		line = new Line[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			line[i] = new Line(start, end);
		}
		
		Arrays.sort(line);
		
		int start = line[0].start;
		int end = line[0].end;
		
		long ans = 0;
		
		int prevStart = 0;
		int prevEnd = 0;
		
		for(int i = 1; i < N; i++) {
			if(line[i].start <= end) {
				end = Math.max(end, line[i].end);
			} else {
				ans += (end - start);
				
				prevStart = start;
				prevEnd = end;
				
				start = line[i].start;
				end = line[i].end;
			}
		}
		
		if(start != prevStart || end != prevEnd) {
			ans += end - start;
		}
		
		System.out.println(ans);
	}
}