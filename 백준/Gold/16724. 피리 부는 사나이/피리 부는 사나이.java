import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;
    public static int[] size;
    public static char[][] board;
    public static Set<Integer> ans;
    public static int N;
    public static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new HashSet<>();

        parent = new int[N * M];
        size = new int[N * M];
        board = new char[N][M];

        for (int i = 0; i < N; i++){
            String line = br.readLine();
            for (int j = 0; j < M; j++){
                board[i][j] = line.charAt(j);
                parent[i * M + j] = i * M + j;
                size[i * M + j] = 1;
            }
        }

        for (int y = 0; y < N; y++){
            for (int x = 0; x < M; x++){
                int ny = y, nx = x;
                if (board[y][x] == 'D'){
                    ny = y + 1;
                    nx = x;
                } else if (board[y][x] == 'U'){
                    ny = y - 1;
                    nx = x;
                } else if (board[y][x] == 'L'){
                    ny = y;
                    nx = x - 1;
                } else if (board[y][x] == 'R'){
                    ny = y;
                    nx = x + 1;
                }

                union(y * M + x, ny * M + nx);
            }
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                ans.add(find(i * M + j));
            }
        }

        System.out.println(ans.size());
    }

    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if (pa != pb){
            if (size[pa] >= size[pb]){
                parent[pb] = pa;
                size[pa] += size[pb];
            } else {
                parent[pa] = pb;
                size[pb] += size[pb];
            }
        }
    }

    public static int find(int x){
        if (x == parent[x]){
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }
}
