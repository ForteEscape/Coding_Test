import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dp;
    static int[][] map;
    static int N;
    static int M;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dp = new int[M][N];
        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[M - 1][N - 1] = 1;
        dfs(0, 0);

        System.out.println(dp[0][0]);
    }

    static void dfs(int y, int x){
        if (y == M - 1 && x == N - 1){
            return;
        }

        if (visited[y][x]){
            return;
        }

        visited[y][x] = true;

        for (int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= M || nx < 0 || nx >= N){
                continue;
            }

            if (map[ny][nx] < map[y][x]){
                if (dp[ny][nx] == 0){
                    dfs(ny, nx);
                }

                dp[y][x] += dp[ny][nx];
            }
        }
    }
}