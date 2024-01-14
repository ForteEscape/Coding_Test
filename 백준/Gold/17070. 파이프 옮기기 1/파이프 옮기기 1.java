import java.io.*;
import java.util.*;

public class Main {

    public static int[][] board;
    public static boolean[][] visited;
    public static int N;
    public static int ans;

    public static int[] dy = {1, 0, 1};
    public static int[] dx = {0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = 0;

        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][1] = true;
        solve(0, 1, 1);

        System.out.println(ans);
    }

    public static void solve(int y, int x, int status){
        if (y == N - 1 && x == N - 1){
            ans++;
            return;
        }

        if (status == 1){ // 가로
            for (int i = 1; i < 3; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (i != 2 && isReachable(ny, nx)){
                    visited[ny][nx] = true;
                    solve(ny, nx, i);
                    visited[ny][nx] = false;
                } else {
                    if (isReachableDiagonal(ny, nx)){
                        visited[ny][nx] = true;
                        visited[y][nx] = true;
                        visited[ny][x] = true;

                        solve(ny, nx, i);

                        visited[ny][nx] = false;
                        visited[y][nx] = false;
                        visited[ny][x] = false;
                    }
                }
            }

        } else if(status == 2){ // 대각

            for (int i = 0; i < 3; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (i != 2 && isReachable(ny, nx)){
                    visited[ny][nx] = true;
                    solve(ny, nx, i);
                    visited[ny][nx] = false;
                } else {
                    if (isReachableDiagonal(ny, nx)){
                        visited[ny][nx] = true;
                        visited[y][nx] = true;
                        visited[ny][x] = true;

                        solve(ny, nx, i);

                        visited[ny][nx] = false;
                        visited[y][nx] = false;
                        visited[ny][x] = false;
                    }
                }
            }
        } else if(status == 0){
            for (int i = 0; i < 3; i++){
                if (i == 1) continue;

                int ny = y + dy[i];
                int nx = x + dx[i];

                if (i != 2 && isReachable(ny, nx)){
                    visited[ny][nx] = true;
                    solve(ny, nx, i);
                    visited[ny][nx] = false;
                } else {
                    if (isReachableDiagonal(ny, nx)){
                        visited[ny][nx] = true;
                        visited[y][nx] = true;
                        visited[ny][x] = true;

                        solve(ny, nx, i);

                        visited[ny][nx] = false;
                        visited[y][nx] = false;
                        visited[ny][x] = false;
                    }
                }
            }
        }
    }

    private static boolean isReachableDiagonal(int y, int x) {
        return y < N && x < N &&
                board[y][x] != 1 && board[y - 1][x] != 1 && board[y][x - 1] != 1 &&
                !visited[y][x] && !visited[y - 1][x] && !visited[y][x - 1];
    }

    public static boolean isReachable(int y, int x){
        return y < N && x < N && board[y][x] != 1 && !visited[y][x];
    }
}

