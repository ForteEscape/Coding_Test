import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int D = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] dduck = new int[D];

    for (int i = 1; i <= K/2; i++){
      for (int j = i + 1; j < K; j++){
        dduck[0] = i;
        dduck[1] = j;

        for (int k = 2; k < D; k++){
          dduck[k] = dduck[k - 2] + dduck[k - 1];
        }

        if (dduck[D - 1] == K){
          System.out.println(dduck[0]);
          System.out.println(dduck[1]);
          return;
        }
      }
    }
  }
}
