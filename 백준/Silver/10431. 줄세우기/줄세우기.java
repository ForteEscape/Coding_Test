import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    for (int testCase = 0; testCase < T; testCase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int testNum = Integer.parseInt(st.nextToken());
      int ans = 0;

      int[] length = new int[20];
      for (int i = 0; i < 20; i++) {
        length[i] = Integer.parseInt(st.nextToken());
      }

      for (int i = 1; i < 20; i++) {
        for (int j = 0; j < i; j++) {
          if (length[j] > length[i]) {
            int temp = length[i];

            for (int k = i; k > j; k--){
              length[k] = length[k - 1];
              ans++;
            }

            length[j] = temp;
            break;
          }
        }
      }

      System.out.println(testNum + " " + ans);
    }
  }
}
