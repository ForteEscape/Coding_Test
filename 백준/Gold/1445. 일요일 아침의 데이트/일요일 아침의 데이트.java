import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Location {
        int y;
        int x;
        int garbage1;
        int garbage2;

        Location(int y, int x, int garbage1, int garbage2) {
            this.y = y;
            this.x = x;
            this.garbage1 = garbage1;
            this.garbage2 = garbage2;
        }
    }

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N, M;
    static char[][] board;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static Location start;
    static boolean[][] visited;
    static List<Point> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++){
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);

                if(line.charAt(j) == 'S') {
                    start = new Location(i, j, 0, 0);
                }

                if (line.charAt(j) == 'g') {
                    list.add(new Point(i, j));
                }
            }
        }

        makeBoard();

        PriorityQueue<Location> pq = new PriorityQueue<>((l1, l2) -> (l1.garbage1 - l2.garbage1) == 0 ?
                (l1.garbage2 - l2.garbage2) : (l1.garbage1 - l2.garbage1));
        pq.offer(start);
        visited[start.y][start.x] = true;

        loop1 : while(!pq.isEmpty()) {
            Location cur = pq.poll();

            for (int i = 0; i <4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                int ng = cur.garbage1;
                int nb = cur.garbage2;

                if (isUnreachable(ny, nx) || visited[ny][nx]) {
                    continue;
                }

                if (board[ny][nx] == 'F') {
                    System.out.println(cur.garbage1 + " " + cur.garbage2);
                    break loop1;
                }

                if (board[ny][nx] == 'g') {
                    ng++;
                }

                if (board[ny][nx] == 'b') {
                    nb++;
                }

                visited[ny][nx] = true;
                pq.offer(new Location(ny, nx, ng, nb));
            }
        }
    }

    public static void makeBoard() {
        for (int i = 0; i < list.size(); i++) {
            Point cur = list.get(i);

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (isUnreachable(ny, nx)) {
                    continue;
                }

                if (board[ny][nx] == '.') {
                    board[ny][nx] = 'b';
                }
            }
        }
    }

    public static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}