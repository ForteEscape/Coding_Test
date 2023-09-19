import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] line = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 1; i <= N; i++){
      int count = Integer.parseInt(st.nextToken());

      for (int j = 1; j <= N; j++){
        if (count == 0){
          if (line[j] == 0){
            line[j] = i;
            break;
          }
        } else if (line[j] == 0){
          count--;
        }
      }
    }

    for (int i = 1; i < line.length; i++){
      System.out.print(line[i] + " ");
    }
  }
}
