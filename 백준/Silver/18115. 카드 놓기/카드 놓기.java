import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    Deque<Integer> deque = new ArrayDeque<>();

    int[] cmd = new int[N + 1];
    for (int i = N; i >= 1; i--){
      cmd[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i <= N; i++){
      if (cmd[i] == 1){
        deque.addFirst(i);
      }

      if (cmd[i] == 2){
        int temp = deque.pollFirst();
        deque.addFirst(i);
        deque.addFirst(temp);
      }

      if (cmd[i] == 3){
        deque.addLast(i);
      }
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int element: deque){
      bw.write(element + " ");
    }
    bw.flush();
  }
}
