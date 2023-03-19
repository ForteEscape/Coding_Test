import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] data = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 100000;
        int start = 0;
        int end = 0;
        int sum = 0;

        while(true){
            if (sum >= S){
                int distance = end - start;
                ans = Math.min(ans, distance);
                sum -= data[start++];
            } else if (end == N){
                break;
            } else {
                sum += data[end++];
            }
        }

        if (ans == 100000){
            System.out.println(0);
        } else{
            System.out.println(ans);
        }
    }
}