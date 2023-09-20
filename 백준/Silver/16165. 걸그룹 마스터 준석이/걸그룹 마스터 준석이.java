import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Map<String, Set<String>> map = new HashMap<>();

    for (int i = 0; i < N; i++){
      String key = br.readLine();

      int num = Integer.parseInt(br.readLine());
      map.put(key, new TreeSet<>());

      for (int j = 0; j < num; j++){
        map.get(key).add(br.readLine());
      }
    }

    for (int i = 0; i < M; i++){
      String question = br.readLine();
      int type = Integer.parseInt(br.readLine());

      if (type == 1){
        for (var data: map.keySet()){
          if (map.get(data).contains(question)){
            System.out.println(data);
            break;
          }
        }
      } else {
        for (var data: map.get(question)){
          System.out.println(data);
        }
      }
    }
  }
}
