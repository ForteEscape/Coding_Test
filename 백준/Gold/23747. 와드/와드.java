import java.util.*;
import java.io.*;

public class Main {

    static class Location {
        int y;
        int x;

        Location(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static char[][] board;
    public static boolean[][] light;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    public static char[] cmd = {'U', 'D', 'L', 'R'};
    public static int N;
    public static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        light = new boolean[N][M];

        for (int i = 0; i < N; i++){
            String line = br.readLine();

            for (int j = 0; j < M; j++){
                board[i][j] = line.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int initY = Integer.parseInt(st.nextToken());
        int initX = Integer.parseInt(st.nextToken());

        Location character = new Location(initY - 1, initX - 1);

        String line = br.readLine();
        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == 'W'){
                if (light[character.y][character.x]){
                    continue;
                }

                bfs(character);
                continue;
            }

            for (int j = 0; j < 4; j++){
                if (line.charAt(i) == cmd[j]){
                    character.y += dy[j];
                    character.x += dx[j];

                    break;
                }
            }
        }

        light[character.y][character.x] = true;
        for (int i = 0; i < 4; i++){
            int ny = character.y + dy[i];
            int nx = character.x + dx[i];

            if (isUnreachable(ny, nx)){
                continue;
            }

            light[ny][nx] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (light[i][j]){
                    sb.append(".");
                } else {
                    sb.append("#");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void bfs(Location character){
        Deque<Location> queue = new ArrayDeque<>();

        queue.addLast(character);
        light[character.y][character.x] = true;

        while(!queue.isEmpty()){
            Location cur = queue.pollFirst();

            for (int i = 0; i < 4; i++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (isUnreachable(ny, nx) || light[ny][nx] || board[ny][nx] != board[character.y][character.x]){
                    continue;
                }

                light[ny][nx] = true;
                queue.addLast(new Location(ny, nx));
            }
        }
    }

    public static boolean isUnreachable(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}