import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    for (int testCase = 0; testCase < T; testCase++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());
      int ans = 0;

      int[] sizeA = new int[A];
      int[] sizeB = new int[B];
      st = new StringTokenizer(br.readLine());

      for (int i = 0; i < A; i++){
        sizeA[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());

      for (int i = 0; i < B; i++){
        sizeB[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(sizeB);

      for (int i = 0; i < A; i++){
        int left = 0;
        int right = B - 1;
        int idx = -1;

        while(left <= right){
          int mid = (left + right) / 2;

          if (sizeB[mid] < sizeA[i]){
            idx = mid;
            left = mid + 1;
          } else{
            right = mid - 1;
          }
        }

        ans += idx + 1;
      }

      System.out.println(ans);
    }
  }
}
