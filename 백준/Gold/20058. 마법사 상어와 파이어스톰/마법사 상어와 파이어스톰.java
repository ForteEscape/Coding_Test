import java.util.*;
import java.io.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, Q;
	static int[][] board;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		board = new int[1 << N][1 << N];
		
		for(int i = 0; i < (1 << N); i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < (1 << N); j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			query(L);
		}
		
		visited = new boolean[(1 << N)][(1 << N)];
		int remainIce = 0;
		int blobCnt = Integer.MIN_VALUE;
		
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				if(board[i][j] != 0) {
					remainIce += board[i][j];
					
					if(!visited[i][j]) {
						visited[i][j] = true;
						int blob = bfs(i, j);
						
						blobCnt = Math.max(blob, blobCnt);
					}
				}
			}
		}
		
		System.out.println(remainIce);
		System.out.println(blobCnt == Integer.MIN_VALUE ? 0 : blobCnt);
	}
	
	public static void query(int level) {
		divide(0, (1 << N) - 1, 0, (1 << N) - 1, N - level);
		melt();
	}
	
	public static int bfs(int y, int x) {
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(new Location(y, x));
		
		int result = 1;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] == 0) {
					continue;
				}
				
				visited[ny][nx] = true;
				result++;
				queue.addLast(new Location(ny, nx));
			}
		}
		
		return result;
	}
	
	private static void divide(int startY, int endY, int startX, int endX, int remain) {
		if(remain == 0) {
			rotate(startY, endY, startX, endX);
			return;
		}
		
		int midY = (startY + endY) / 2;
		int midX = (startX + endX) / 2;
		
		divide(startY, midY, startX, midX, remain - 1);
		divide(startY, midY, midX + 1, endX, remain - 1);
		divide(midY + 1, endY, startX, midX, remain - 1);
		divide(midY + 1, endY, midX + 1, endX, remain - 1);
	}
	
	private static void melt() {
		List<Location> meltList = new ArrayList<>();
		
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				
				if(board[i][j] == 0) continue;
				
				int adjIceCnt = 0;
				for(int dir = 0; dir < 4; dir++) {
					int ny = i + dy[dir];
					int nx = j + dx[dir];
					
					if(isUnreachable(ny, nx)) {
						continue;
					}
					
					if(board[ny][nx] != 0) {
						adjIceCnt++;
					}
				}
				
				if(adjIceCnt < 3) {
					meltList.add(new Location(i, j));
				}
				
			}
		}
		
		for(Location melt : meltList) {
			board[melt.y][melt.x]--;
		}
	}
	
	private static void rotate(int startY, int endY, int startX, int endX) {
		int length = (endY - startY) + 1;
		int[][] tempBoard = new int[length][length];
		
		for(int i = startY, tempX = length - 1; i <= endY; i++, tempX--) {
			for(int j = startX, tempY = 0; j <= endX; j++, tempY++) {
				tempBoard[tempY][tempX] = board[i][j];
			}
		}
		
		for(int i = startY, tempY = 0; i <= endY; i++, tempY++) {
			for(int j = startX, tempX = 0; j <= endX; j++, tempX++) {
				board[i][j] = tempBoard[tempY][tempX];
			}
		}
	}
    
	private static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= (1 << N) || x < 0 || x >= (1 << N);
	}
	
}