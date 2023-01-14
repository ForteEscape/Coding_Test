import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Location{
    int x;
    int y;
    int z;

    Location(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        int[][][] board = new int[2][N + 1][M + 1];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                int status = Integer.parseInt(st.nextToken());
                board[0][i][j] = status;
                board[1][i][j] = status;
            }
        }

        Deque<Location> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N + 1][M + 1];
        int ans = -1;

        queue.add(new Location(0, 0, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Location location = queue.pop();

            int y = location.y;
            int x = location.x;
            int sword = location.z;

            if(y == N - 1 && x == M - 1){
                ans = board[sword][y][x];
                break;
            }

            for(int i = 0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= M){
                    continue;
                }

                if(sword == 0 && !visited[sword][ny][nx] && board[sword][ny][nx] != 1){
                    visited[sword][ny][nx] = true;

                    if(board[sword][ny][nx] == 2){
                        board[sword + 1][ny][nx] = board[sword][y][x] + 1;
                        visited[sword + 1][ny][nx] = true;
                        queue.add(new Location(nx, ny, sword + 1));
                    }
                    else{
                        board[sword][ny][nx] = board[sword][y][x] + 1;
                        queue.add(new Location(nx, ny, sword));
                    }
                }
                else if(sword == 1 && !visited[sword][ny][nx]){
                    visited[sword][ny][nx] = true;
                    board[sword][ny][nx] = board[sword][y][x] + 1;
                    queue.add(new Location(nx,ny, sword));
                }
            }
        }

        if(ans > 0 && ans <= T){
            bw.write(String.valueOf(ans));
            bw.newLine();
        }
        else{
            bw.write("Fail\n");
        }
        bw.flush();
    }
}