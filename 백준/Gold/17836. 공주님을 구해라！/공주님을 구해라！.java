import java.util.*;
import java.io.*;

public class Main {
    
    static class Location {
        int y;
        int x;
        int cnt;
        int status;
        
        Location(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        Location(int y, int x, int cnt, int status) {
            this(y, x);
            this.cnt = cnt;
            this.status = status;
        }
    }
    
    public static int[][] board;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static int N, M, T;
    public static Location gram;
    public static boolean[][][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                
                if(board[i][j] == 2) {
                    gram = new Location(i, j);
                }
            }
        }
        
        int time = bfs(0, 0);
        
        if(time > T) {
            System.out.println("Fail");
        } else {
            System.out.println(time);   
        }
    }
    
    public static int bfs(int y, int x) {
        visited = new boolean[2][N][M];
        Deque<Location> queue = new ArrayDeque<>();
        
        visited[0][y][x] = true;
        queue.addLast(new Location(y, x, 0, 0));
        
        int ans = Integer.MAX_VALUE;
        
        while(!queue.isEmpty()) {
            Location cur = queue.pollFirst();
            
            if(cur.y == N - 1 && cur.x == M - 1) {
                ans = Math.min(ans, cur.cnt);
            }
            
            if(cur.status == 0 && cur.y == gram.y && cur.x == gram.x) {
                cur.status = 1;
            }
            
            for(int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                
                if(isValid(ny, nx) || visited[cur.status][ny][nx]) {
                    continue;
                }
                
                if(cur.status == 0 && board[ny][nx] == 1) {
                    continue;
                }
                
                visited[cur.status][ny][nx] = true;
                queue.addLast(new Location(ny, nx, cur.cnt + 1, cur.status));
            }
        }
        
        return ans;
    }
    
    private static boolean isValid(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}
