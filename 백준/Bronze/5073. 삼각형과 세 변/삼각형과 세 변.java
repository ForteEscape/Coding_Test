import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while(true){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] length = new int[3];
      int cnt = 0;

      for (int i = 0; i < 3; i++){
        length[i] = Integer.parseInt(st.nextToken());
        if (length[i] == 0){
          cnt++;
        }
      }

      if (cnt == 3){
        break;
      }

      Arrays.sort(length);

      if (length[2] >= length[0] + length[1]){
        System.out.println("Invalid");
        continue;
      }

      if (length[0] == length[1] && length[1] == length[2]){
        System.out.println("Equilateral");
      } else if ((length[0] == length[1] && length[1] != length[2]) || (length[1] == length[2] && length[0] != length[1])){
        System.out.println("Isosceles");
      } else if (length[0] != length[1] && length[1] != length[2]){
        System.out.println("Scalene");
      }
    }
  }
}
