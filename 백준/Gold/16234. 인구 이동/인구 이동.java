import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Location {
		int y;
		int x;
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static boolean[][] visited;
	static int[][] board;
	static List<Location> changeList;
	static int N, L, R;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 두 나라 간 인구 수 차이가 L명 이상 R명 이하가 될 때 까지 BFS로 Legion을 설정한다.
		// BFS에서는 위의 조건에 맞는 인구들을 모두 합산하여 반환해야 한다.
		// 반환한 인구 합을 Legion에 포함된 영역의 수 만큼 나눈다. => BFS 내부에서 연산 가능할 것으로 생각된다.
		// update 할 legion을 보관 후 갱신한다.
		int ans = 0;
		while(true) {
			visited = new boolean[N][N];
			
			boolean flag = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						visited[i][j] = true;
						int distributeAmount = bfs(i, j);
						
						if(distributeAmount == -1) {
							continue;
						}
						flag = true;
						distribute(distributeAmount);
					}
				}
			}
			
			if(!flag) {
				break;
			}
			ans++;
		}
		
		System.out.println(ans);
	}
	
	public static int bfs(int y, int x) {
		changeList = new ArrayList<>();
		Deque<Location> queue = new ArrayDeque<>();
		
		Location start = new Location(y, x);
		queue.addLast(new Location(y, x));
		changeList.add(start);
		
		int amount = board[y][x];
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			Location cur = queue.pollFirst();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(isUnreachable(ny, nx) || visited[ny][nx]) {
					continue;
				}
				
				int diff = Math.abs(board[cur.y][cur.x] - board[ny][nx]);
				if(diff >= L && diff <= R) {
					visited[ny][nx] = true;
					amount += board[ny][nx];
					cnt++;
					
					Location next = new Location(ny, nx);
					queue.addLast(next);
					changeList.add(next);
				}
			}
		}
		
		return cnt == 0 ? -1 : (amount / (cnt + 1));
	}
	
	public static void distribute(int distributeAmount) {
		for(Location element : changeList) {
			board[element.y][element.x] = distributeAmount;
		}
	}
	
	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}