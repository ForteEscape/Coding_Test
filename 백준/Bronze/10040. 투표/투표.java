import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] vote = new int[N + 1];
    int[] cost = new int[N + 1];

    for (int i = 1; i <= N; i++){
      cost[i] = Integer.parseInt(br.readLine());
    }

    int maxVote = -1;
    int ans = -1;

    for (int i = 0; i < M; i++){
      int standard = Integer.parseInt(br.readLine());

      for (int j = 1; j <= N; j++){
        if (cost[j] <= standard){
          vote[j]++;

          if (maxVote < vote[j]){
            maxVote = vote[j];
            ans = j;
          }

          break;
        }
      }
    }
    System.out.println(ans);
  }
}
