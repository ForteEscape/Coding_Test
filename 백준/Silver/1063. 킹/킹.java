import java.util.*;
import java.io.*;

public class Main {

    static int[] dy = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static String[] dir = {"B", "T", "R", "L", "RT", "LT", "RB", "LB"};

    static class Location {
        int y;
        int x;

        Location(int x, int y){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String kingLocation = st.nextToken();
        String stoneLocation = st.nextToken();
        int N = Integer.parseInt(st.nextToken());

        Location king = new Location(kingLocation.charAt(0) - 'A' + 1, kingLocation.charAt(1) - '0');
        Location stone = new Location(stoneLocation.charAt(0) - 'A' + 1, stoneLocation.charAt(1) - '0');

        for (int i = 0; i < N; i++){
            String cmd = br.readLine();

            for (int j = 0; j < 8; j++){
                if (cmd.equals(dir[j])){
                    int ny = king.y + dy[j];
                    int nx = king.x + dx[j];

                    if (isUnreachable(ny, nx)){
                        continue;
                    }

                    if (ny == stone.y && nx == stone.x){
                        int stoneNy = stone.y + dy[j];
                        int stoneNx = stone.x + dx[j];

                        if (isUnreachable(stoneNy, stoneNx)){
                            continue;
                        }

                        stone.y = stoneNy;
                        stone.x = stoneNx;
                    }

                    king.y = ny;
                    king.x = nx;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append((char)('A' + king.x - 1)).append(king.y);
        System.out.println(sb);
        
        sb = new StringBuilder();
        sb.append((char)('A' + stone.x - 1)).append(stone.y);
        System.out.println(sb);
    }

    public static boolean isUnreachable(int y, int x){
        return y <= 0 || y > 8 || x <= 0 || x > 8;
    }
}