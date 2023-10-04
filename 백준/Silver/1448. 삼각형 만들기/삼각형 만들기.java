import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] data = new int[N];

    for (int i = 0; i < N; i++){
      data[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(data);

    for (int i = data.length - 1; i >= 2; i--){
      int line1 = data[i];
      int line2 = data[i - 1];
      int line3 = data[i - 2];

      if (line1 >= line2 + line3){
        continue;
      }

      System.out.println(line1 + line2 + line3);
      return;
    }

    System.out.println(-1);
  }
}
