import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int testCase = 0; testCase < T; testCase++){
      Map<String, Integer> map = new HashMap<>();

      int N = Integer.parseInt(br.readLine());
      for (int i = 0; i < N; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        String key = st.nextToken();
        map.put(key, map.getOrDefault(key, 0) + 1);
      }

      int ans = 1;
      for (var element: map.keySet()){
        ans *= map.get(element) + 1;
      }

      System.out.println(ans - 1);
    }
  }
}
