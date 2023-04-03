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
        int[] data = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int ans = 0;

        while(start <= N && end <= N){
            if (sum >= M){
                if (sum == M){
                    ans++;
                }
                sum -= data[start++];

            } else if (end == N){
                break;
            } else{
                sum += data[end++];
            }
        }

        System.out.println(ans);
    }
}