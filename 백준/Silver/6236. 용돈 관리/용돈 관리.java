import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] money = new int[N];
    long sum = 0L;
    long start = Long.MIN_VALUE;

    for (int i = 0; i < N; i++){
      money[i] = Integer.parseInt(br.readLine());
      sum += money[i];

      start = Math.max(start, money[i]);
    }

    long end = sum;
    long ans = Long.MAX_VALUE;

    while(start <= end){
      long mid = (start + end) / 2;

      int cnt = 1;
      long currentSum = 0L;

      for (int i = 0; i < N; i++){
        if (currentSum + money[i] > mid){
          currentSum = 0L;
          cnt++;
        }

        currentSum += money[i];
      }

      if (cnt > M){
        start = mid + 1;
      } else {
        ans = Math.min(ans, end);
        end = mid - 1;
      }
    }

    System.out.println(ans);
  }
}
