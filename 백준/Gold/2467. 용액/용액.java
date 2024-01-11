import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;
        long result;
        long pivot = Integer.MAX_VALUE;

        int[] ans = {data[start], data[end]};

        while(start < end){
            result = data[start] + data[end];

            if (pivot > Math.abs(result)){
                pivot = Math.abs(result);
                ans[0] = data[start];
                ans[1] = data[end];
            }

            if (result == 0){
                System.out.println(data[start] + " " + data[end]);
                return;
            } else if (result < 0){
                start++;
            } else {
                end--;
            }
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}
