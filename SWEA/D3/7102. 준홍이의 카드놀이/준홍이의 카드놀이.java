import java.util.*;
import java.io.*;

public class Solution {

  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      Integer[][] cnt = new Integer[N + M + 1][2];

      for (int i = 0; i <= N + M; i++) {
        cnt[i][0] = 0;
        cnt[i][1] = 0;
      }

      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
          cnt[i + j][0] = i + j;
          cnt[i + j][1]++;
        }
      }

      Arrays.sort(cnt, (x, y) -> y[1] - x[1] == 0 ? x[0] - y[0] : y[1] - x[1]);
      int first = cnt[0][0];
      int firstCnt = cnt[0][1];

      StringBuilder sb = new StringBuilder("#" + test_case + " " + first + " ");
      for (int i = 1; i <= N + M; i++) {
        if (cnt[i][1] != firstCnt) {
          break;
        }

        sb.append(cnt[i][0]).append(" ");
      }

      System.out.println(sb);
    }
  }
}