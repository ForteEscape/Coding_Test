import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String data = br.readLine();
    String[] divided = new String[3];
    PriorityQueue<String> pq = new PriorityQueue<>();

    for (int i = 1; i <= data.length() - 2; i++){
      divided[0] = data.substring(0, i);

      for (int j = i + 1; j <= data.length() - 1; j++){
        divided[1] = data.substring(i, j);
        divided[2] = data.substring(j);

        StringBuilder result = new StringBuilder();

        for (int k = 0; k < divided.length; k++){
          StringBuffer sb = new StringBuffer(divided[k]);
          result.append(sb.reverse().toString());
        }

        pq.offer(result.toString());
      }
    }

    System.out.println(pq.poll());
  }
}
