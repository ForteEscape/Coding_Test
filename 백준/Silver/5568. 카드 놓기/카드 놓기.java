import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static Set<String> answer = new HashSet<>();
  static boolean[] visit;
  static String[] numbers;
  static int N;
  static int K;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());

    visit = new boolean[N];

    numbers = new String[N];

    for (int i = 0; i < N; i++){
      numbers[i] = br.readLine();
    }

    dfs(0, "");

    System.out.println(answer.size());
  }

  public static void dfs(int currentPic, String currentNumber){
    if (currentPic == K){
      answer.add(currentNumber);
      return;
    }

    for (int i = 0; i < N; i++){
      if (!visit[i]){
        visit[i] = true;
        dfs(currentPic + 1, currentNumber + numbers[i]);
        visit[i] = false;
      }
    }
  }
}
