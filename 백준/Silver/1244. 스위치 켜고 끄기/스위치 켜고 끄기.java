import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int[] switchArr;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    switchArr = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());
    int idx = 1;

    while (st.hasMoreTokens()) {
      switchArr[idx++] = Integer.parseInt(st.nextToken());
    }

    int studentNum = Integer.parseInt(br.readLine());

    for (int i = 0; i < studentNum; i++) {
      st = new StringTokenizer(br.readLine());

      int gender = Integer.parseInt(st.nextToken());
      int startIdx = Integer.parseInt(st.nextToken());

      changeStatus(gender, startIdx);
    }

    for (int i = 1; i < switchArr.length; i++){
      System.out.print(switchArr[i] + " ");

      if (i % 20 == 0) {
        System.out.println();
      }
    }
  }

  public static void changeStatus(int gender, int startIdx) {
    if (gender == 1) {
      for (int i = startIdx; i < switchArr.length; i += startIdx) {
        switchArr[i] = 1 - switchArr[i];
      }
    } else {

      int left = startIdx - 1;
      int right = startIdx + 1;
      switchArr[startIdx] = 1 - switchArr[startIdx];

      while (true) {
        if (left <= 0 || left >= switchArr.length || right <= 0 || right >= switchArr.length ||
            switchArr[left] != switchArr[right]
        ) {
          break;
        }

        switchArr[left] = 1 - switchArr[left];
        switchArr[right] = 1 - switchArr[right];

        left--;
        right++;
      }
    }
  }
}
