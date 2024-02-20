import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[M + 1];

        Arrays.fill(dp, -1);
        dp[S] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int vol = Integer.parseInt(st.nextToken());
            List<Integer> availableList = new ArrayList<>();

            for (int j = 0; j <= M; j++) {
                if (dp[j] == i - 1) {
                    int upVol = j + vol;
                    int downVol = j - vol;

                    if (0 <= upVol && upVol <= M) {
                        availableList.add(upVol);
                    }

                    if(0 <= downVol && downVol <= M) {
                        availableList.add(downVol);
                    }
                }
            }

            for (int element : availableList) {
                dp[element] = i;
            }
        }

        int ans = -1;
        for (int i = 0; i <= M; i++) {
            if(dp[i] == N) {
                ans = Math.max(ans, i);
            }
        }

        System.out.println(ans);
    }
}