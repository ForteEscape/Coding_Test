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

    int[] trees = new int[N];

    long start = 0;
    long end = 0;

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++){
      trees[i] = Integer.parseInt(st.nextToken());
      end = Math.max(end, trees[i]);
    }

    while(start < end){
      long mid = (start + end) / 2;

      if (cut(mid, trees) >= M){
        start = mid + 1;
      } else{
        end = mid;
      }
    }

    System.out.println(start - 1);
  }

  public static long cut(long cutHeight, int[] trees){
    long cnt = 0;

    for (int tree : trees) {
      if (tree > cutHeight) {
        cnt += tree - cutHeight;
      }
    }

    return cnt;
  }
}
