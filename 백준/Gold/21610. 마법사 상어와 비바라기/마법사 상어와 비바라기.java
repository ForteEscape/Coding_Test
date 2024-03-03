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

    static int[][] board;
    static int N, M;
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static List<Location> cloudList;
    static boolean[][] deleteCloud;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloudList = new ArrayList<>();
        cloudList.add(new Location(N - 1, 0));
        cloudList.add(new Location(N - 1, 1));
        cloudList.add(new Location(N - 2, 0));
        cloudList.add(new Location(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int direction = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            move(direction, speed);
            rain();
            waterCopy();
            cloudClear();
            createCloud();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += board[i][j];
            }
        }
        System.out.println(ans);
    }

    public static void move(int dir, int speed) {
        for (Location cloud : cloudList) {
            cloud.y = (cloud.y + ((dy[dir] * speed) % N) + N) % N;
            cloud.x = (cloud.x + ((dx[dir] * speed) % N) + N) % N;
        }
    }

    public static void rain() {
        for (Location cloud : cloudList) {
            board[cloud.y][cloud.x]++;
        }
    }

    public static void cloudClear() {
        deleteCloud = new boolean[N][N];

        for (Location cloud : cloudList) {
            deleteCloud[cloud.y][cloud.x] = true;
        }

        cloudList.clear();
    }

    public static void waterCopy() {
        for (Location cloud : cloudList) {
            for (int i = 2; i <= 8; i += 2) {
                int ny = cloud.y + dy[i];
                int nx = cloud.x + dx[i];

                if(isUnreachable(ny, nx) || board[ny][nx] == 0) {
                    continue;
                }

                board[cloud.y][cloud.x]++;
            }
        }
    }

    public static void createCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] >= 2 && !deleteCloud[i][j]) {
                    cloudList.add(new Location(i, j));
                    board[i][j] -= 2;
                }
            }
        }
    }

    private static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}