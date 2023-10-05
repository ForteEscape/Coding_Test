import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int ans = 1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] fee = new int[4];
    for (int i = 1; i <= 3; i++){
      fee[i] = Integer.parseInt(st.nextToken());
    }

    int[][] parkTime = new int[3][2];
    for (int i = 0; i < 3; i++){
      st = new StringTokenizer(br.readLine());
      parkTime[i][0] = Integer.parseInt(st.nextToken());
      parkTime[i][1] = Integer.parseInt(st.nextToken());
    }

    int cnt = 0;
    int ans = 0;
    for (int i = 1; i <= 100; i++){

      ans += fee[cnt] * cnt;

      for (int j = 0; j < 3; j++){
        if (parkTime[j][0] == i){
          cnt++;
        }

        if (parkTime[j][1] == i){
          cnt--;
        }
      }
    }

    System.out.println(ans);
  }
}
