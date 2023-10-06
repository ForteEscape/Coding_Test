import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());
    int Y = Integer.parseInt(st.nextToken());

    if (A + B > C * 2){
      int temp2 = Math.max(X, Y);

      int result1 = temp2 * C * 2;
      int result2;

      if (X < Y){
        result2 = X * C * 2;
        result2 += (Y - X) * B;
      } else {
        result2 = Y * C * 2;
        result2 += (X - Y) * A;
      }

      System.out.println(Math.min(result1, result2));
    } else {
      System.out.println(A * X + B * Y);
    }
  }
}
