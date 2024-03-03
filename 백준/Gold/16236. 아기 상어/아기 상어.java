import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Location implements Comparable<Location>{
        int y;
        int x;
        int size;
        int cnt;
        int time;

        Location(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        Location(int y, int x, int size, int cnt, int time) {
            this(y, x, time);
            this.size = size;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Location o) {
            if (this.time == o.time) {
                if (this.y == o.y) {
                    return this.x - o.x;
                }
                return this.y - o.y;
            }
            return this.time - o.time;
        }
    }

    static int[][] board;
    static int N;
    static boolean[][] visited;
    static Location babyShark;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int ans;
    static PriorityQueue<Location> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 9) {
                    babyShark = new Location(i, j, 2, 0, 0);
                    board[i][j] = 0;
                }
            }
        }

        ans = 0;
        while(true) {
            if(!searchFish()) {
                break;
            }
            update();
        }

        System.out.println(ans);
    }

    public static boolean searchFish() {
        visited = new boolean[N][N];
        Deque<Location> queue = new ArrayDeque<>();
        pq = new PriorityQueue<>();
        visited[babyShark.y][babyShark.x] = true;
        queue.addLast(babyShark);

        while(!queue.isEmpty()) {
            Location cur = queue.pollFirst();

            if (board[cur.y][cur.x] != 0 && board[cur.y][cur.x] < cur.size) {
                pq.offer(new Location(cur.y, cur.x, cur.time));
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] > cur.size) {
                    continue;
                }

                visited[ny][nx] = true;
                queue.addLast(new Location(ny, nx, cur.size, cur.cnt, cur.time + 1));
            }
        }

        return pq.size() != 0;
    }

    public static void update(){
        Location fish = pq.poll();
        board[fish.y][fish.x] = 0;

        babyShark.y = fish.y;
        babyShark.x = fish.x;
        babyShark.cnt++;

        if (babyShark.cnt == babyShark.size) {
            babyShark.cnt = 0;
            babyShark.size++;
        }

        ans += fish.time;
    }

    public static boolean isUnreachable(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}