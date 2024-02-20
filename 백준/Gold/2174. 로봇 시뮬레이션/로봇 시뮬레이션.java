import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		int dir;
		
		Location(int y, int x, int dir) {
			this.y =y ;
			this.x = x;
			this.dir = dir;
		}
	}
	
	static int N, M;
	static int robotInit, query;
	static Map<Character, Integer> directionMap;
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	static Location[] robotLocation;
	
	static {
		directionMap = new HashMap<>();
		directionMap.put('N', 0);
		directionMap.put('E', 1);
		directionMap.put('S', 2);
		directionMap.put('W', 3);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		robotInit = Integer.parseInt(st.nextToken());
		query = Integer.parseInt(st.nextToken());
		
		robotLocation = new Location[robotInit + 1];
		
		for(int i = 1; i <= robotInit; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			
			robotLocation[i] = new Location(y, x, directionMap.get(d));
		}
		
		for(int i = 0; i < query; i++) {
			st = new StringTokenizer(br.readLine());
			
			int tgt = Integer.parseInt(st.nextToken());
			char order = st.nextToken().charAt(0);
			int loop = Integer.parseInt(st.nextToken());
			
			// test
			for(int j = 0; j < loop; j++) {
				if(order == 'R') {
					robotLocation[tgt].dir = (robotLocation[tgt].dir + 1) % 4;
				} else if(order == 'L') {
					robotLocation[tgt].dir = robotLocation[tgt].dir == 0 ? 3 : robotLocation[tgt].dir - 1;
				} else if(order == 'F') {
					int ny = robotLocation[tgt].y + dy[robotLocation[tgt].dir];
					int nx = robotLocation[tgt].x + dx[robotLocation[tgt].dir];
					
					if(isUnreachable(ny, nx)) {
						System.out.println("Robot " + tgt + " crashes into the wall");
						return;
					}
					
					for(int k = 1; k <= robotInit; k++) {
						if(k == tgt) continue;
						
						if(robotLocation[k].y == ny && robotLocation[k].x == nx) {
							System.out.println("Robot " + tgt + " crashes into robot " + k);
							return;
						}
					}
					
					robotLocation[tgt].y = ny;
					robotLocation[tgt].x = nx;
				}
			}
		}
		
		System.out.println("OK");
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y <= 0 || y > N || x <= 0 || x > M;
	}
}