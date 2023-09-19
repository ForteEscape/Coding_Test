import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int answer = 1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] data = new int[N][M];
    for (int i = 0; i < N; i++){
      String line = br.readLine();

      for (int j = 0; j < M; j++){
        data[i][j] = line.charAt(j) - '0';
      }
    }

    for (int i = 2; i <= Math.min(N, M); i++){
      checkSquare(data, i, N, M);
    }

    System.out.println(answer * answer);
  }

  public static void checkSquare(int[][] data, int size, int N, int M){
    for (int j = 0; j <= N - size; j++){
      for (int k = 0; k <= M - size; k++){
        if (data[j][k] == data[j][k + size - 1] && data[j][k] == data[j + size - 1][k + size - 1] && data[j][k] == data[j + size - 1][k]){
          answer = size;
          return;
        }
      }
    }
  }
}
