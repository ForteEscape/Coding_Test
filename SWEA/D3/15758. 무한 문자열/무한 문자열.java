import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution {

  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      String str1 = st.nextToken();
      String str2 = st.nextToken();

      if (str1.equals(str2)) {
        System.out.println("#" + test_case + " yes");
        continue;
      }

      int lcs = str1.length() * str2.length();
      StringBuilder res1 = new StringBuilder(str1);
      StringBuilder res2 = new StringBuilder(str2);

      while (res1.length() != lcs || res2.length() != lcs) {
        if (res1.length() != lcs) {
          res1.append(str1);
        }

        if (res2.length() != lcs) {
          res2.append(str2);
        }
      }

      if (res1.toString().equals(res2.toString())) {
        System.out.println("#" + test_case + " yes");
      } else {
        System.out.println("#" + test_case + " no");
      }
    }
  }
}