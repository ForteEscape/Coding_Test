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

        int[] counter = new int[N];
        int max = -1;

        for (int i = 0; i < N; i++){
            counter[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, counter[i]);
        }

        long left = 0L; // 0초가 가장 적은 시간
        long right = max * 1000000000L; // 카운터에서 가장 많이 걸리는 시간 * 인원 수 가 실질적으로 있을 수 있는 최대 시간
        long ans = 0L;

        while(left <= right){
            long mid = (left + right) / 2;
            long cnt = 0;

            for (int i = 0; i < N; i++){
                cnt += (mid / counter[i]);
                
                if(cnt >= M) break;
            }

            // 인원수보다 많은 경우
            if (M <= cnt){
                ans = mid;
                right = mid - 1;
            } else if(cnt < M){
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
