import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int blackMaxCount = 0;
    static int whiteMaxCount = 0;
    static int[] dy = {-1, -1, 1, 1};
    static int[] dx = {-1, 1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        blackCount(0, 0, 0);

        visited = new boolean[N][N];
        whiteCount(0, 1, 0);

        System.out.println(blackMaxCount + whiteMaxCount);
    }

    static void blackCount(int y, int x, int cnt) {
        blackMaxCount = Math.max(cnt, blackMaxCount);

        if (x >= N) {
            y += 1;
            x = y % 2 == 0 ? 0 : 1;
        }

        if (y >= N) {
            return;
        }

        if (canDeploy(y, x)) {
            visited[y][x] = true;
            blackCount(y, x + 2, cnt + 1);
            visited[y][x] = false;
        }

        blackCount(y, x + 2, cnt);
    }

    static void whiteCount(int y, int x, int cnt) {
        whiteMaxCount = Math.max(cnt, whiteMaxCount);

        if (x >= N) {
            y += 1;
            x = y % 2 == 0 ? 1 : 0;
        }

        if (y >= N) {
            return;
        }

        if (canDeploy(y, x)) {
            visited[y][x] = true;
            whiteCount(y, x + 2, cnt + 1);
            visited[y][x] = false;
        }

        whiteCount(y, x + 2, cnt);
    }

    static boolean canDeploy(int y, int x) {
        if (board[y][x] == 0) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            for (int j = 1; j < N; j++) {
                if (!isUnreachable(ny, nx)) {
                    if (visited[ny][nx]) {
                        return false;
                    }

                    ny += dy[i];
                    nx += dx[i];
                } else {
                    break;
                }
            }
        }

        return true;
    }

    static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}