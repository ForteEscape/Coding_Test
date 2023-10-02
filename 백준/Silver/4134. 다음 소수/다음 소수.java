import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int testCase = 0; testCase < T; testCase++){
      long N = Long.parseLong(br.readLine());

      if (N <= 2){
        System.out.println(2);
        continue;
      }

      while(true){
        boolean flag = true;

        for (int i = 2; i < Math.sqrt(N) + 1; i++){
          if (N % i == 0){
            flag = false;
            break;
          }
        }

        if (flag) {
          System.out.println(N);
          break;
        }

        N++;
      }
    }
  }
}
