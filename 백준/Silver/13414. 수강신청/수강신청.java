import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());

    LinkedHashSet<String> set = new LinkedHashSet<>();

    for (int i = 0; i < L; i++) {
      String data = br.readLine();

      if (set.contains(data)) {
        set.remove(data);
      }
      set.add(data);
    }

    int count = 0;
    for (var element : set) {
      count++;
      System.out.println(element);

      if (count == N) {
        break;
      }
    }
  }
}
