import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Location {
		int y;
		int x;
		int dist;
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
			this.dist = 0;
		}
		
		Location(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
	static int[][] board;
	static int[][] simulation;
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
	static boolean[][] visited;
	static int N, M, D;
	static List<Location> locationList;
	static int ans;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        locationList = new ArrayList<>();
        simulation = new int[N][M];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < M; j++) {
        		board[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        ans = Integer.MIN_VALUE;
        for(int i = 0; i < M - 2; i++) {
        	for(int j = i + 1; j < M - 1; j++) {
        		for(int k = j + 1; k < M; k++) {
        			
        			chk(i, j, k);
        		}
        	}
        }
        
        System.out.println(ans);
    }
    
    public static void init() {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			simulation[i][j] = board[i][j];
    		}
     	}
    }
    
    public static void chk(int first, int second, int third) {
    	init();
    	int result = 0;
    	int cnt = 0;
    	
    	Location firstLocation = new Location(N, first);
    	Location secondLocation = new Location(N, second);
    	Location thirdLocation = new Location(N, third);
    	
    	while(cnt < N) {
    		Location enemyFirst = bfs(firstLocation);
        	Location enemySecond = bfs(secondLocation);
        	Location enemyThird = bfs(thirdLocation);
        	
        	
        	if(enemyFirst != null && simulation[enemyFirst.y][enemyFirst.x] == 1) {
        		simulation[enemyFirst.y][enemyFirst.x] = 0;
        		result++;
        	}
        	
        	if(enemySecond != null && simulation[enemySecond.y][enemySecond.x] == 1) {
        		simulation[enemySecond.y][enemySecond.x] = 0;
        		result++;
        	}
        	
        	if(enemyThird != null && simulation[enemyThird.y][enemyThird.x] == 1) {
        		simulation[enemyThird.y][enemyThird.x] = 0;
        		result++;
        	}
        	
        	ans = Math.max(ans, result);
        	movement();
        	cnt++;
    	}
    }
    
    public static void movement() {
    	for(int i = N - 1; i > 0; i--) {
    		for(int j = 0; j < M; j++) {
    			simulation[i][j] = simulation[i - 1][j];
    		}
    	}
    	
    	for(int i = 0; i < M; i++) {
    		simulation[0][i] = 0;
    	}
    }
    
    public static Location bfs(Location location) {
    	visited = new boolean[N][M];
    	Deque<Location> queue = new ArrayDeque<>();
    	queue.addLast(location);
    	int idx = 1;
    	
    	while(!queue.isEmpty()) {
    		Location cur = queue.pollFirst();
    		
    		if(cur.dist > D) {
    			break;
    		}
    		
    		for(int j = 0; j < 4; j++) {
    			int ny = cur.y + dy[j];
    			int nx = cur.x + dx[j];
    			
    			if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) {
    				continue;
    			}
    			
    			if(simulation[ny][nx] == 1 && cur.dist + 1 <= D) {
        			return new Location(ny, nx);
        		}
    			
    			visited[ny][nx] = true;
    			queue.addLast(new Location(ny, nx, cur.dist + 1));
    		}
    	}
    	
    	return null;
    }
}