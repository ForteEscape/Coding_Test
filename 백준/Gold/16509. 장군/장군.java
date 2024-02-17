import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Location {
        int y;
        int x;
        int dist;

        Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Location(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    static int[][] dy = {{-1, -1, -1}, {0, -1, -1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 1}, {0, 1, 1}, {0, -1, -1}, {-1, -1, -1}};
    static int[][] dx = {{0, -1, -1}, {-1, -1, -1}, {-1, -1, -1}, {0, -1, -1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 1}, {0, 1, 1}};
    static Location p;
    static Location king;
    static boolean[][] visited;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        p = new Location(y, x, 0);

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        king = new Location(y, x);

        board = new int[10][9];
        board[king.y][king.x] = 1;

        System.out.println(bfs(p));
    }

    public static int bfs(Location p) {
        Deque<Location> queue = new ArrayDeque<>();
        queue.addLast(p);

        visited = new boolean[10][9];
        visited[p.y][p.x] = true;

        while(!queue.isEmpty()) {
            Location cur = queue.pollFirst();

            if (cur.y == king.y && cur.x == king.x) {
                return cur.dist;
            }

            for (int i = 0; i < 8; i++) {
                int ny = cur.y;
                int nx = cur.x;

                boolean flag = false;
                for (int j = 0; j < 3; j++) {
                    int tempy = ny + dy[i][j];
                    int tempx = nx + dx[i][j];

                    if (isUnreachable(tempy, tempx) || (board[tempy][tempx] == 1 && j < 2)) {
                        flag = true;
                        break;
                    }

                    ny = tempy;
                    nx = tempx;
                }

                if (flag || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                queue.addLast(new Location(ny, nx, cur.dist + 1));
            }
        }

        return -1;
    }

    public static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= 10 || x < 0 || x >= 9;
    }
}