import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int limitTime = 210;
    int K = Integer.parseInt(br.readLine());
    int N = Integer.parseInt(br.readLine());

    int currentTime = 0;

    for (int i = 0; i < N; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      currentTime += Integer.parseInt(st.nextToken());
      if (currentTime >= limitTime){
        System.out.println(K);
        break;
      }

      String status = st.nextToken();
      if (status.equals("T")){
        if (K == 8){
          K = 1;
        } else{
          K++;
        }
      }
    }
  }
}
