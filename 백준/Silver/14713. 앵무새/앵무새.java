import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    ArrayDeque<String>[] queueArr = new ArrayDeque[N];
    Set<String> words = new HashSet<>();

    for (int i = 0; i < N; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      queueArr[i] = new ArrayDeque<>();

      while(st.hasMoreTokens()){
        String data = st.nextToken();

        words.add(data);
        queueArr[i].addLast(data);
      }
    }

    StringTokenizer st = new StringTokenizer(br.readLine());
    Set<String> set = new HashSet<>();

    while(st.hasMoreTokens()){
      boolean flag = false;
      String word = st.nextToken();

      if (set.contains(word) || !words.contains(word)){
        System.out.println("Impossible");
        return;
      }

      set.add(word);

      for (int i = 0; i < N; i++){
        if (queueArr[i].isEmpty()) continue;

        if (word.equals(queueArr[i].peekFirst())){
          queueArr[i].pollFirst();
          flag = true;
          break;
        }
      }

      if (!flag){
        System.out.println("Impossible");
        return;
      }
    }
    
    if (set.size() != words.size()){
      System.out.println("Impossible");
      return;
    }

    System.out.println("Possible");
  }
}
