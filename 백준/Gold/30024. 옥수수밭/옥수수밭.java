import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static class Node{
    int y;
    int x;
    int value;

    Node(int y, int x, int value){
      this.y = y;
      this.x = x;
      this.value = value;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> y.value - x.value);

    int[][] board = new int[N][M];
    int[][] visited = new int[N][M];

    for (int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++){
        int price = Integer.parseInt(st.nextToken());

        if (i == 0 || i == N - 1){
          pq.offer(new Node(i, j, price));
          visited[i][j] = 1;
        } else{
          if(j == 0 || j == M - 1){
            pq.offer(new Node(i, j, price));
            visited[i][j] = 1;
          }
        }
        board[i][j] = price;
      }
    }

    int K = Integer.parseInt(br.readLine());

    while(K > 0){
      Node cur = pq.poll();

      visited[cur.y][cur.x] = 2;
      System.out.println((cur.y + 1) + " " + (cur.x + 1));

      for (int i = 0; i < 4; i++){
        int ny = cur.y + dy[i];
        int nx = cur.x + dx[i];

        if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] == 2){
          continue;
        }

        if (visited[ny][nx] == 0){
          visited[ny][nx] = 1;
          pq.offer(new Node(ny, nx, board[ny][nx]));
        }
      }

      K--;
    }
  }
}
