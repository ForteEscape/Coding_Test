import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Location {
		int y;
		int x;
		int key;
		int cnt;
		
		Location(int y, int x, int key, int cnt) {
			this.y = y;
			this.x = x;
			this.key = key;
			this.cnt = cnt;
		}
	}
	
	static char[][] board;
	static boolean[][][] visited;
	static Map<Character, Integer> keyMap;
	static Map<Character, Integer> doorMap;
	static int N, M;
	static Location start;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static {
		keyMap = new HashMap<>();
		doorMap = new HashMap<>();
		
		keyMap.put('a', 0);keyMap.put('b', 1);
		keyMap.put('c', 2);keyMap.put('d', 3);
		keyMap.put('e', 4);keyMap.put('f', 5);
		
		doorMap.put('A', 0);doorMap.put('B', 1);
		doorMap.put('C', 2);doorMap.put('D', 3);
		doorMap.put('E', 4);doorMap.put('F', 5);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		start= null;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
				
				if(board[i][j] == '0') {
					start = new Location(i, j, 0, 0);
					board[i][j] = '.';
				}
			}
		}
		
		visited = new boolean[1 << 6][N][M];
		bfs();
		System.out.println(-1);
	}
	
	static void bfs() {
		visited[0][start.y][start.x] = true;
		
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(start);
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			if(board[cur.y][cur.x] == '1') {
				System.out.println(cur.cnt);
				System.exit(0);
			}
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || board[ny][nx] == '#') {
					continue;
				}
				
				if(doorMap.containsKey(board[ny][nx])) {
					if(hasKey(cur.key, board[ny][nx]) && !visited[cur.key][ny][nx]) {
						visited[cur.key][ny][nx] = true;
						queue.addLast(new Location(ny, nx, cur.key, cur.cnt + 1));
					} else {
						continue;
					}
					continue;
				}
				
				if(keyMap.containsKey(board[ny][nx])) {
					int newKey = cur.key | (1 << keyMap.get(board[ny][nx]));
					if(!visited[newKey][ny][nx]) {
						visited[newKey][ny][nx] = true;
						queue.addLast(new Location(ny, nx, newKey, cur.cnt + 1));
					} else {
						continue;
					}
					continue;
				}
				
				if(!visited[cur.key][ny][nx]) {
					visited[cur.key][ny][nx] = true;
					queue.addLast(new Location(ny, nx, cur.key, cur.cnt + 1));
				}
			}
		}
	}
	
	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
	
	static boolean hasKey(int currentKey, char door) {
		return (currentKey & (1 << doorMap.get(door))) == (1 << doorMap.get(door));
	}
}