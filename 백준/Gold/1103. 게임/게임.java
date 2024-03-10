import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char data = line.charAt(j);

                if (data == 'H') {
                    board[i][j] = 0;
                } else {
                    board[i][j] = data - '0';
                }
            }
        }

        dp = new int[N][M];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        visited = new boolean[N][M];

        int ans = dfs(0, 0);
        System.out.println(ans);
    }

    public static int dfs(int y, int x) {
        if(isUnreachable(y, x) || board[y][x] == 0) {
            return 0;
        }

        if (visited[y][x]) {
            System.out.println(-1);
            System.exit(0);
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        visited[y][x] = true;
        dp[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i] * board[y][x];
            int nx = x + dx[i] * board[y][x];

            dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
        }

        visited[y][x] = false;
        return dp[y][x];
    }

    private static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}