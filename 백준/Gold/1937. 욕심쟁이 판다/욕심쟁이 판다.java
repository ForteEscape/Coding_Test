import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] board;
    static int N;
    static StringTokenizer st;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }

        System.out.println(ans);
    }

    // dfs 탐색, 탐색 도중에  dp 갱신
    // dp가 이미 존재한다면 굳이 더 탐색할 필요 없음. 바로 브레이크
    public static int dfs(int y, int x) {
        if (dp[y][x] != 0){
            return dp[y][x];
        }

        dp[y][x] = 1;

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(isUnreachable(ny, nx) || board[ny][nx] <= board[y][x]) {
                continue;
            }

            dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
            dfs(ny, nx);
        }

        return dp[y][x];
    }

    public static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }

}