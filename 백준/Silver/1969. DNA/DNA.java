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

    String[] data = new String[N];
    char[] dnaElement = {'A', 'C', 'G', 'T'};
    int hammingDistance = 0;

    for (int i = 0; i < N; i++){
      data[i] = br.readLine();
    }

    StringBuilder sb = new StringBuilder();

    for (int j = 0; j < M; j++){
      int[] count = new int[4];
      int maxCount = -1;
      int maxCountIdx = -1;

      for (int i = 0; i < N; i++){
        char element = data[i].charAt(j);

        if (element == 'A'){
          count[0]++;
        } else if (element == 'C'){
          count[1]++;
        } else if (element == 'G'){
          count[2]++;
        } else if (element == 'T'){
          count[3]++;
        }
      }

      for (int i = 0; i < 4; i++){
        if (count[i] > maxCount){
          maxCount = count[i];
          maxCountIdx = i;
        }
      }

      sb.append(dnaElement[maxCountIdx]);
      hammingDistance += N - count[maxCountIdx];
    }

    System.out.println(sb.toString());
    System.out.println(hammingDistance);

  }
}
