import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] data = new int[N];

        for (int i = 0; i < N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);

        long ans = Long.MAX_VALUE;
        long sum = 0L;
        long sum_abs = 0L;
        int[] result = new int[2];

        int start = 0;
        int end = N - 1;

        while(start < end){
            sum = data[start] + data[end];
            sum_abs = Math.abs(sum);

            if (ans > sum_abs){
                ans = sum_abs;
                result[0] = data[start];
                result[1] = data[end];
            }

            if (sum > 0){
                end--;
            } else{
                start++;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}