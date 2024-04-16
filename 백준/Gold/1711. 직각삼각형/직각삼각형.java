import java.util.*;
import java.io.*;

public class Main {
	
	static class Pair {
		long x;
		long y;
		
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static Pair[] point;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		point = new Pair[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			point[i] = new Pair(x, y);
		}
		
		int ans = 0;
		for(int i = 0; i < N - 2; i++) {
			Pair p1 = point[i];
			for(int j = i + 1; j < N - 1; j++) {
				Pair p2 = point[j];
				for(int k = j + 1; k < N; k++) {
					Pair p3 = point[k];
					
					long dist1 = getDistance(p1, p2);
					long dist2 = getDistance(p1, p3);
					long dist3 = getDistance(p2, p3);
					
					if(isPitagoras(dist1, dist2, dist3)) {
						ans++;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static long getDistance(Pair p1, Pair p2) {
		return ((p1.y - p2.y) * (p1.y - p2.y)) + ((p1.x - p2.x) * (p1.x - p2.x));
	}
	
	static boolean isPitagoras(long dist1, long dist2, long dist3) {
		long maxDist = Math.max(dist1, Math.max(dist2, dist3));
		
		if(maxDist == dist1) {
			return maxDist == dist2 + dist3;
		} else if(maxDist == dist2) {
			return maxDist == dist1 + dist3;
		} else {
			return maxDist == dist2 + dist1;
		}
	}

}