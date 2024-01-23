import java.util.*;
import java.io.*;

public class Main {

    static int R;
    static int C;
    static int K;
    static char[][] board;
    static int ans;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++){
            String line = br.readLine();
            for (int j = 0; j < C; j++){
                board[i][j] = line.charAt(j);
            }
        }

        ans = 0;
        visited[R - 1][0] = true;
        solve(R - 1, 0, 1);

        System.out.println(ans);
    }

    public static void solve(int y, int x, int length){
        if (y == 0 && x == C - 1){
            if (length == K){
                ans++;
            }
        }

        for (int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(isUnreachable(ny, nx)){
                continue;
            }

            visited[ny][nx] = true;
            solve(ny, nx, length + 1);
            visited[ny][nx] = false;
        }
    }

    public static boolean isUnreachable(int y, int x){
        return y < 0 || y >= R || x < 0 || x >= C || visited[y][x] || board[y][x] == 'T';
    }
}