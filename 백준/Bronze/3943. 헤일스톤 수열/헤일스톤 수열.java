import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    for (int testCase = 0; testCase < T; testCase++){
      int N = Integer.parseInt(br.readLine());
      int ans = N;

      while(N != 1){
        ans = Math.max(ans, N);

        if (N % 2 == 0){
          N /= 2;
        } else{
          N = N * 3 + 1;
        }
      }

      System.out.println(ans);
    }
  }
}
