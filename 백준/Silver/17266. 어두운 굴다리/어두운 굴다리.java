import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    int[] lampLocation = new int[M];
    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < M; i++){
      int location = Integer.parseInt(st.nextToken());
      lampLocation[i] = location;
    }

    int start = 1;
    int end = N;
    int ans = 0;

    while(start <= end){
      int mid = (start + end) / 2;

      int temp = 0;
      for (int location: lampLocation){
        if (location - mid > temp){
          start = mid + 1;
          break;
        }

        temp = location + mid;
      }

      if (temp < N){
        start = mid + 1;
        continue;
      }

      ans = mid;
      end = mid - 1;

    }

    System.out.println(ans);
  }
}
