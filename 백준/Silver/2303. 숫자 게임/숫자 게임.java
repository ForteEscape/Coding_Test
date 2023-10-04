import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[][] numbers = new int[N][5];
    int ansValue = Integer.MIN_VALUE;
    int ansIdx = -1;

    for (int i = 0; i < N; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int maxValue = Integer.MIN_VALUE;

      for (int j = 0; j < 5; j++){
        numbers[i][j] = Integer.parseInt(st.nextToken());
      }

      for (int first = 0; first < 3; first++){
        for (int second = first + 1; second < 4; second++){
          for (int third = second + 1; third < 5; third++){
            int result = (numbers[i][first] + numbers[i][second] + numbers[i][third]) % 10;

            if (maxValue <= result){
              maxValue = result;
            }
          }
        }
      }

      if (ansValue <= maxValue){
        ansValue = maxValue;
        ansIdx = i;
      }
    }

    System.out.println(ansIdx + 1);
  }
}
