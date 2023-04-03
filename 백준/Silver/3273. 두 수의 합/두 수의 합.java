import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);

        int K = Integer.parseInt(br.readLine());

        int start = 0;
        int end = N - 1;
        int ans = 0;

        while(start < end){
            int sum = data[start] + data[end];

            if (sum < K){
                start++;
            } else{
                if (sum == K){
                    ans++;
                }

                end--;
            }
        }

        System.out.println(ans);
    }
}