import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int INF = 100000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        HashSet<Integer> coinSet = new HashSet<>();

        for (int i = 0; i < N; i++){
            coinSet.add(Integer.parseInt(br.readLine()));
        }

        int[] coins = new int[coinSet.size()];
        int idx = 0;
        for (int element: coinSet){
            coins[idx++] = element;
        }

        for (int i = 0; i < coinSet.size(); i++){
            for (int j = coins[i]; j <= K; j++){
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        
        if(dp[K] == INF){
            System.out.println("-1");
        } else{
            System.out.println(dp[K]);
        }
    }
}