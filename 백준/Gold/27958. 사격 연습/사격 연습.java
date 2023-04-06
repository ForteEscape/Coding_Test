import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static int[][] tmpBoard;
    static ArrayList<ArrayList<Integer>> per;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int ans = -1;

        board = new int[N][N];
        tmpBoard = new int[N][N];

        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] bullet = new int[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++){
            bullet[i] = Integer.parseInt(st.nextToken());
        }

        int[] row = new int[N];
        for(int i = 0; i < N; i++){
            row[i] = i;
        }
        int[] out = new int[K];

        boolean[] visited = new boolean[N];
        per = new ArrayList<>();

        getPermutation(row, N, K, 0, visited, out);

        for (ArrayList<Integer> element: per){
            init();
            int scoreSum = 0;
            int bulletIdx = 0;
            boolean[][] isFragment = new boolean[N][N];

            for(int shootingRow : element) {
                for (int i = 0; i < N; i++) {
                    if (tmpBoard[shootingRow][i] == 0) {
                        continue;
                    }

                    if (tmpBoard[shootingRow][i] >= 10) {
                        scoreSum += tmpBoard[shootingRow][i];
                        tmpBoard[shootingRow][i] = 0;
                        break;
                    } else {
                        if (tmpBoard[shootingRow][i] <= bullet[bulletIdx]) {
                            if (isFragment[shootingRow][i]) { // 파편화된 경우
                                scoreSum += tmpBoard[shootingRow][i];
                            } else {
                                scoreSum += board[shootingRow][i];
                            }
                            tmpBoard[shootingRow][i] = 0;

                            int uy = shootingRow - 1;
                            int dy = shootingRow + 1;
                            int lx = i - 1;
                            int rx = i + 1;
                            int fragment;

                            if (uy >= 0 && tmpBoard[uy][i] == 0) {
                                if (board[shootingRow][i] == 0){
                                    fragment = tmpBoard[shootingRow][i] / 4;
                                    if (fragment != 0){
                                        isFragment[uy][i] = true;
                                    }
                                    tmpBoard[uy][i] = fragment;

                                } else{
                                    fragment = board[shootingRow][i] / 4;
                                    if (fragment != 0){
                                        isFragment[uy][i] = true;
                                    }
                                    tmpBoard[uy][i] = board[shootingRow][i] / 4;
                                }
                            }

                            if (dy < N && tmpBoard[dy][i] == 0) {
                                if (board[shootingRow][i] == 0){
                                    fragment = tmpBoard[shootingRow][i] / 4;

                                    if (fragment != 0){
                                        isFragment[dy][i] = true;
                                    }

                                    tmpBoard[dy][i] = fragment;
                                } else{
                                    fragment = board[shootingRow][i] / 4;

                                    if (fragment != 0){
                                        isFragment[dy][i] = true;
                                    }

                                    tmpBoard[dy][i] = board[shootingRow][i] / 4;
                                }
                            }

                            if (lx >= 0 && tmpBoard[shootingRow][lx] == 0) {
                                if (board[shootingRow][i] == 0){
                                    fragment = tmpBoard[shootingRow][i] / 4;

                                    if (fragment != 0){
                                        isFragment[shootingRow][lx] = true;
                                    }

                                    tmpBoard[shootingRow][lx] = fragment;
                                } else{
                                    fragment = board[shootingRow][i] / 4;

                                    if (fragment != 0){
                                        isFragment[shootingRow][lx] = true;
                                    }

                                    tmpBoard[shootingRow][lx] = fragment;
                                }
                            }

                            if (rx < N && tmpBoard[shootingRow][rx] == 0) {
                                if (board[shootingRow][i] == 0){
                                    fragment = tmpBoard[shootingRow][i] / 4;

                                    if (fragment != 0){
                                        isFragment[shootingRow][rx] = true;
                                    }

                                    tmpBoard[shootingRow][rx] = fragment;
                                } else{
                                    fragment = board[shootingRow][i] / 4;

                                    if (fragment != 0){
                                        isFragment[shootingRow][rx] = true;
                                    }
                                    tmpBoard[shootingRow][rx] = fragment;
                                }
                            }
                        } else {
                            tmpBoard[shootingRow][i] -= bullet[bulletIdx];
                        }
                        break;
                    }
                }
                bulletIdx++;
            }
            
            ans = Math.max(ans, scoreSum);
        }

        System.out.println(ans);
    }

    public static void init(){
        for (int i = 0; i < N; i++){
            System.arraycopy(board[i], 0, tmpBoard[i], 0, N);
        }
    }

    public static void getPermutation(int[] arr, int N, int R, int depth, boolean[] visited, int[] out){
        if (depth == R){
            ArrayList<Integer> result = new ArrayList<>();
            for(int element: out){
                result.add(element);
            }

            per.add(result);
            return;
        }

        for (int i = 0; i < N; i++){
            out[depth] = arr[i];
            getPermutation(arr, N, R, depth + 1, visited, out);
            out[depth] = 0;
        }
    }

    public static void solve(){

    }
}