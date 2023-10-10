import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] lectureLength = new int[N];

    long sum = 0L;
    int maxLectureLength = Integer.MIN_VALUE;
    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++){
      lectureLength[i] = Integer.parseInt(st.nextToken());
      maxLectureLength = Math.max(maxLectureLength, lectureLength[i]);
      sum += lectureLength[i];
    }

    if (N == M){
      System.out.println(maxLectureLength);
      return;
    }

    if (M == 1){
      System.out.println(sum);
      return;
    }

    long start = maxLectureLength;
    long end = sum;
    long ans = Long.MAX_VALUE;

    while(start < end){
      long mid = (start + end) / 2;

      long partSum = 0L;
      int cnt = 1;

      for (int i = 0; i < N; i++){
        if (partSum + lectureLength[i] > mid){
          partSum = 0L;
          cnt++;
        }

        partSum += lectureLength[i];
      }

      if (cnt > M){
        start = mid + 1;
      } else if (cnt <= M){
        ans = Math.min(ans, mid);
        end = mid;
      }
    }

    System.out.println(ans);
  }
}
