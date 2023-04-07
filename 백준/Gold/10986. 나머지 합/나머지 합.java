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

        long[] prefixSum = new long[N + 1];
        long[] remainder = new long[M + 1];

        st = new StringTokenizer(br.readLine());
        prefixSum[0] = 0;

        long ans = 0;

        for (int i = 1; i <= N; i++){
            int number = Integer.parseInt(st.nextToken());
            prefixSum[i] = (prefixSum[i - 1] + number) % M;

            if (prefixSum[i] == 0){
                ans++;
            }

            remainder[(int)prefixSum[i]]++;
        }

        for(int i = 0; i < M; i++){
            ans += ((remainder[i] * (remainder[i] - 1)) / 2);
        }

        System.out.println(ans);
    }
}
