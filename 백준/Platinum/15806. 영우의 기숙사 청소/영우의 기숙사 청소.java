import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		int day;
		
		Location(int y, int x, int day) {
			this.y = y;
			this.x = x;
			this.day = day;
		}
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, M, K, D;
	static Deque<Location> queue;
	static List<Location> checkList;
	static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		queue = new ArrayDeque<>();
		checkList = new ArrayList<>();
		visited = new boolean[2][N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			queue.addLast(new Location(y, x, 0));
			visited[0][y][x] = true;
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			checkList.add(new Location(y, x));
		}
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			if(cur.day >= D) {
				continue;
			}
			
			boolean flag = false;
			
			for(int i = 0; i < 8; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx)) {
					continue;
				}
				
				int currentDay = (cur.day + 1) % 2;
				if(visited[currentDay][ny][nx]) {
					continue;
				}
				
				visited[currentDay][ny][nx] = true;
				queue.addLast(new Location(ny, nx, cur.day + 1));
				flag = true;
			}
			
			if(!flag) {
				visited[cur.day % 2][cur.y][cur.x] = false;
			}
		}
		
		int checkDay = D % 2;
		for(Location check : checkList) {
			if(visited[checkDay][check.y][check.x]) {
				System.out.println("YES");
				return;
			}
		}
		
		System.out.println("NO");
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 1 || y >= N || x < 1 || x >= N;
	}
	
}