import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String targetString = br.readLine();
    int N = Integer.parseInt(br.readLine());
    int ans = 0;

    for (int i = 0; i < N; i++){
      String data = br.readLine();

      for (int j = 0; j < 10; j++){
        StringBuilder sb = new StringBuilder();

        for (int k = j; k < j + targetString.length(); k++){
          sb.append(data.charAt(k % 10));
        }

        if (targetString.equals(sb.toString())){
          ans++;
          break;
        }
      }
    }

    System.out.println(ans);
  }
}
