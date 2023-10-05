import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int ans = 1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String data = br.readLine();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < data.length(); i++){
      if ('A' <= data.charAt(i) && data.charAt(i) <= 'Z'){
        char result = (char)((((data.charAt(i) - 'A') + 13) % 26) + 'A');
        sb.append(result);
      } else if ('a' <= data.charAt(i) && data.charAt(i) <= 'z'){
        char result = (char)((((data.charAt(i) - 'a') + 13) % 26) + 'a');
        sb.append(result);
      } else {
        sb.append(data.charAt(i));
      }
    }

    System.out.println(sb.toString());
  }
}
