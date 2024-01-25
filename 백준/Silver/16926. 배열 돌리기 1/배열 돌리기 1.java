import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(R > 0){
            R--;
            rotate(board);
        }

        print(board);
    }

    public static void rotate(int[][] board){
        int group = Math.min(board.length, board[0].length) / 2;

        for(int i = 0; i < group; i++){
            int temp = board[i][i];

            for (int j = i + 1; j < board[0].length - i; j++){
                board[i][j - 1] = board[i][j];
            }

            for (int j = i + 1; j < board.length - i; j++){
                board[j - 1][board[0].length - i - 1] = board[j][board[0].length - i - 1];
            }

            for(int j = board[0].length - i - 1; j >= i + 1; j--){
                board[board.length - i - 1][j] = board[board.length - i - 1][j - 1];
            }

            for(int j = board.length - i - 1; j >= i + 1; j--){
                board[j][i] = board[j - 1][i];
            }

            board[i + 1][i] = temp;
        }
    }

    public static void print(int[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}