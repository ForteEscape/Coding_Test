import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		int cnt;
		
		Location(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static int[][] board;
	static List<Location> whiteLocation;
	static List<Location> candidateList;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static boolean[][] visited;
	static int res, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		whiteLocation = new ArrayList<>();
		candidateList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == 0) {
					candidateList.add(new Location(i, j, 0));
				}
				
				if(board[i][j] == 2) {
					whiteLocation.add(new Location(i, j, 1));
				}
			}
		}
		
		ans = Integer.MIN_VALUE;
		for(int i = 0; i < candidateList.size() - 1; i++) {
			for(int j = i + 1; j < candidateList.size(); j++) {
				Location candidate1 = candidateList.get(i);
				Location candidate2 = candidateList.get(j);
				
				board[candidate1.y][candidate1.x] = 1;
				board[candidate2.y][candidate2.x] = 1;
				
				chk();
				
				board[candidate1.y][candidate1.x] = 0;
				board[candidate2.y][candidate2.x] = 0;
			}
		}
		
		System.out.println(ans);
	}
	
	public static void chk() {
		visited = new boolean[N][M];
		res = 0;
		
		for(Location white : whiteLocation) {
			if(!visited[white.y][white.x]) {
				visited[white.y][white.x] = true;
				bfs(white);
			}
		}
		
		ans = Math.max(ans, res);
	}
	
	public static void bfs(Location start) {
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(start);
		
		int cnt = 1;
		boolean flag = true;
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] == 1) {
					continue;
				}
				
				if(board[ny][nx] == 0) {
					flag = false;
					continue;
				}
				
				if(board[ny][nx] == 2) {
					visited[ny][nx] = true;
					queue.addLast(new Location(ny, nx, 0));
					cnt++;
				}
			}
		}
		
		if(flag) {
			res += cnt;
		}
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}