import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];

        for (int i = 0; i < N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        int idx = coins.length - 1;
        int ans = 0;

        while(K > 0 && idx >= 0){
            if (coins[idx] > K){
                idx--;
                continue;
            }

            ans += (K / coins[idx]);
            K = K % coins[idx];
            idx--;
        }

        System.out.println(ans);
    }
}