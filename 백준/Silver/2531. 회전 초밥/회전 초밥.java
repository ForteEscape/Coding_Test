import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    int[] sushiTable = new int[N + 1];
    for (int i = 0; i < N; i++){
      sushiTable[i] = Integer.parseInt(br.readLine());
    }

    int ans = 0;
    int count = 1;

    int[] eatingList = new int[D + 1];
    eatingList[C] = 3001;

    for (int i = 0; i < K; i++){
      int sushiType = sushiTable[i];

      if (eatingList[sushiType] == 0){
        count++;
      }

      eatingList[sushiType]++;
    }

    ans = count;

    for (int i = 0; i < N - 1; i++){
      int start = sushiTable[i];
      int end = sushiTable[i + K < N ? i + K : (i + K) % N];

      if (--eatingList[start] == 0){
        count--;
      }

      if (++eatingList[end] == 1){
        count++;
      }

      ans = Math.max(count, ans);
    }

    System.out.println(ans);
  }
}
