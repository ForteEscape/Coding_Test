import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] dx = {1, -1, 2};

    if (N >= K){
      System.out.println(N - K + "\n1");
      return;
    }

    Deque<Integer> queue = new ArrayDeque<>();
    int[] time = new int[100001];
    int cnt = 0;

    int ans = Integer.MAX_VALUE;
    time[N] = 1;
    queue.addLast(N);

    while(!queue.isEmpty()){
      int cur = queue.pollFirst();

      if (time[cur] > ans){
        continue;
      }

      for (int i = 0; i < 3; i++){
        int nx;

        if (i < 2){
          nx = cur + dx[i];
        } else{
          nx = cur * dx[i];
        }

        if (nx < 0 || nx > 100000){
          continue;
        }

        if (nx == K){
          ans = time[cur];
          cnt++;
        }

        if (time[nx] == 0 || time[nx] == time[cur] + 1){
          queue.addLast(nx);
          time[nx] = time[cur] + 1;
        }
      }
    }

    System.out.println(ans + "\n" + cnt);
  }
}
