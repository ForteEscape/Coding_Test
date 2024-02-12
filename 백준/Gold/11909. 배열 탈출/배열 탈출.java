import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Location {
        int y;
        int x;
        int cost;

        Location(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }

    static int[][] board;
    static int[][] cost;
    static int N;
    static int[] dy = {1, 0};
    static int[] dx = {0, 1};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[N + 1][N + 1];
        cost = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                cost[i][j] = INF;
            }
        }

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        PriorityQueue<Location> pq = new PriorityQueue<>((l1, l2) -> l1.cost - l2.cost);
        Location start = new Location(1, 1, 0);
        pq.offer(start);

        cost[1][1] = 0;

        while(!pq.isEmpty()) {
            Location cur = pq.poll();

            if (cost[cur.y][cur.x] < cur.cost) {
                continue;
            }

            if (cur.y == N && cur.x == N) {
                return cur.cost;
            }

            if ((1 <= cur.y && cur.y < N) && (1 <= cur.x && cur.x < N)) {
                for (int i = 0; i < 2; i++) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];

                    if (isUnreachable(ny, nx)) {
                        continue;
                    }

                    int weight = 0;
                    if (board[ny][nx] >= board[cur.y][cur.x]) {
                        weight = (board[ny][nx] - board[cur.y][cur.x]) + 1;;
                    }

                    if (cost[ny][nx] > cur.cost + weight) {
                        cost[ny][nx] = cur.cost + weight;
                        pq.offer(new Location(ny, nx, cost[ny][nx]));
                    }
                }
            }  else {
                int ny = cur.y;
                int nx = cur.x;

                if (cur.y == N && (1 <= cur.x && cur.x < N)) {
                    nx++;
                } else if ((1 <= cur.y && cur.y < N) && cur.x == N) {
                    ny++;
                }

                if (isUnreachable(ny, nx)) {
                    continue;
                }

                int weight = 0;
                if (board[ny][nx] >= board[cur.y][cur.x]) {
                    weight = (board[ny][nx] - board[cur.y][cur.x]) + 1;
                }

                if (cost[ny][nx] > cur.cost + weight) {
                    cost[ny][nx] = cur.cost + weight;
                    pq.offer(new Location(ny, nx, cost[ny][nx]));
                }
            }
        }

        return -1;
    }

    static boolean isUnreachable(int y, int x) {
        return y < 1 || y > N || x < 1 || x > N ;
    }
}