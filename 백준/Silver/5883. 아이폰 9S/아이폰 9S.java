import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int ans = 1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[] line = new int[N];
    Set<Integer> capacity = new HashSet<>();

    for (int i = 0; i < N; i++){
      line[i] = Integer.parseInt(br.readLine());
      capacity.add(line[i]);
    }

    for (var removeElement: capacity){
      solve(removeElement, line);
    }

    System.out.println(ans);
  }

  public static void solve(int element, int[] line){
    int cnt = 1;

    int pre = line[0];
    for (int i = 1; i < line.length; i++){
      if (line[i] == element) continue;
      if (pre != line[i]){
        cnt = 1;
      } else{
        cnt++;
        ans = Math.max(ans, cnt);
      }

      pre = line[i];
    }
  }
}
