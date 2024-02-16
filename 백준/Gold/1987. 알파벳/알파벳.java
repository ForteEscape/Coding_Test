import java.io.*;
import java.util.*;

public class Main {
	
	static class Location {
		int y;
		int x;
		int dist;
		
		Location(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
	
	static int N, M;
	static char[][] board;
	static Set<Character> pathSet;
	static boolean[][] visited;
	static int ans;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new char[N][M];
        visited = new boolean[N][M];
        
        pathSet = new HashSet<>();
        for(int i = 0; i < N; i++) {
        	String line = br.readLine();
        	for(int j = 0; j < M; j++) {
        		board[i][j] = line.charAt(j);
        	}
        }
        visited[0][0] = true;
        pathSet.add(board[0][0]);
        ans = Integer.MIN_VALUE;
        
        solve(new Location(0, 0, 1));
        
        System.out.println(ans);
    }
    
    public static void solve(Location location) {
    	ans = Math.max(ans, location.dist);
    	
    	for(int i = 0; i < 4; i++) {
    		int ny = location.y + dy[i];
    		int nx = location.x + dx[i];
    		
    		if(isUnreachable(ny, nx) || visited[ny][nx] || pathSet.contains(board[ny][nx])) {
    			continue;
    		}
    		
    		visited[ny][nx] = true;
    		pathSet.add(board[ny][nx]);
    		solve(new Location(ny, nx, location.dist + 1));
    		visited[ny][nx] = false;
    		pathSet.remove(board[ny][nx]);
    	}
    }
    
    public static boolean isUnreachable(int y, int x) {
    	return y < 0 || y >= N || x < 0 || x >= M;
    }
}