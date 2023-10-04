import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    String target = br.readLine();
    int ans = 0;

    for (int i = 0; i < N - 1; i++){
      String data = br.readLine();
      int cnt = 0;

      int[] wordData = new int[26];
      for (int j = 0; j < target.length(); j++){
        wordData[target.charAt(j) - 'A']++;
      }

      for (int j = 0; j < data.length(); j++){
        if (wordData[data.charAt(j) - 'A'] > 0){
          wordData[data.charAt(j) - 'A']--;
          cnt++;
        }
      }

      if (target.length() == data.length() && (target.length() == cnt || target.length() - 1 == cnt)){
        ans++;
      } else if (target.length() == data.length() - 1 && data.length() - 1 == cnt){
        ans++;
      } else if (target.length() == data.length() + 1 && data.length() == cnt){
        ans++;
      }
    }

    System.out.println(ans);
  }
}
