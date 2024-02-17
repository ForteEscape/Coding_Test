import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Location {
        int y;
        int x;

        Location(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        ans = new int[]{0, 0};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <M; j++) {
                if (board[i][j] != '#' && !visited[i][j]){
                    visited[i][j] = true;
                    bfs(i, j);
                }
            }
        }

        System.out.println(ans[1] + " " + ans[0]);
    }

    public static void bfs(int y, int x) {
        Deque<Location> queue = new ArrayDeque<>();
        queue.addLast(new Location(y, x));

        int[] cnt = new int[2];

        if (board[y][x] == 'v') {
            cnt[0]++;
        } else if (board[y][x] == 'k') {
            cnt[1]++;
        }

        while(!queue.isEmpty()) {
            Location cur = queue.pollFirst();

            for(int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] == '#') {
                    continue;
                }

                visited[ny][nx] = true;
                if(board[ny][nx] == 'v') {
                    cnt[0]++;
                } else if (board[ny][nx] == 'k') {
                    cnt[1]++;
                }

                queue.addLast(new Location(ny, nx));
            }
        }

        if (cnt[0] >= cnt[1]) {
            ans[0] += cnt[0];
        } else {
            ans[1] += cnt[1];
        }
    }

    public static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}