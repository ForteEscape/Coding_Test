import java.util.*;
import java.io.*;

public class Main {

    public static int maxValue = Integer.MIN_VALUE;
    public static int minValue = Integer.MAX_VALUE;
    public static int N;
    public static int[][] board;
    public static boolean[][] visited;

    static class Location {
        int y;
        int x;

        Location(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                maxValue = Math.max(maxValue, board[i][j]);
                minValue = Math.min(minValue, board[i][j]);
            }
        }

        int left = 0;
        int right = maxValue - minValue;
        int mid;
        int answer = Integer.MAX_VALUE;

        while(left <= right){
            mid = (left + right) / 2;
            boolean flag = false;

            for (int i = minValue; i <= maxValue; i++){
                if (i <= board[0][0] && board[0][0] <= i + mid){
                    flag = bfs(i, i + mid);

                    if (flag){
                        break;
                    }
                }
            }

            if (flag){
                right = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean bfs(int min, int max){
        Deque<Location> queue = new ArrayDeque<>();
        queue.addLast(new Location(0, 0));

        visited = new boolean[N][N];
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Location cur = queue.pollFirst();

            if (cur.y == N - 1 && cur.x == N - 1){
                return true;
            }

            for (int i = 0; i < 4; i++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] < min || board[ny][nx] > max){
                    continue;
                }

                visited[ny][nx] = true;
                queue.addLast(new Location(ny, nx));
            }
        }

        return false;
    }

    public static boolean isUnreachable(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}