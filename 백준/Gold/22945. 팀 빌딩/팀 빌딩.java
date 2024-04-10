import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] data;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        long ans = Long.MIN_VALUE;

        while(left < right) {
            long res = Math.min(data[left], data[right]) * (right - left - 1L);
            ans = Math.max(ans, res);

            if (data[left] < data[right]) {
                left ++;
            } else if(data[left] == data[right]) {
                left++;
                right--;
            } else {
                right--;
            }
        }

        System.out.println(ans);
    }
}