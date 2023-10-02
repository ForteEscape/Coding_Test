import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static String[][] board;
  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {1, -1, 0, 0};
  static Set<String> answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    board = new String[5][5];
    answer = new HashSet<>();

    for (int i = 0; i < 5; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      for (int j = 0; j < 5; j++){
        board[i][j] = st.nextToken();
      }
    }

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        dfs(i, j, board[i][j], 0);
      }
    }

    System.out.println(answer.size());
  }

  public static void dfs(int y, int x, String currentString, int moveCount){
    if (moveCount == 5){
      answer.add(currentString);
      return;
    }

    for (int i = 0; i < 4; i++){
      int ny = y + dy[i];
      int nx = x + dx[i];

      if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5){
        continue;
      }

      dfs(ny, nx, currentString + board[ny][nx], moveCount + 1);
    }
  }
}
