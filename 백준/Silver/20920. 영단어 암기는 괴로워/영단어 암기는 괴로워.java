import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Map<String, Integer> wordCount = new HashMap<>();

    for (int i = 0; i < N; i++){
      String data = br.readLine();

      if (data.length() < M){
        continue;
      }

      wordCount.put(data, wordCount.getOrDefault(data, 0) + 1);
    }

    List<String> keys = new ArrayList<>(wordCount.keySet());
    keys.sort((x, y) -> {
      if (wordCount.get(y) - wordCount.get(x) == 0){
        if (y.length() - x.length() == 0){
          return x.compareTo(y);
        } else{
          return y.length() - x.length();
        }
      } else{
        return wordCount.get(y) - wordCount.get(x);
      }
    });

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    for (String key : keys){
      bw.write(key + "\n");
    }
    bw.flush();
  }
}
