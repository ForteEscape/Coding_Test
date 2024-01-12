import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long min = Long.MAX_VALUE;

        int[] data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);

        int start = 0, end = 0, mid = 0;
        int ans1 = 0, ans2 = 0, ans3 = 0;

        loop1: for (int i = 0; i < N - 2; i++){
            start = i;
            end = N - 1;
            mid = i + 1;

            while(end > mid){
                long result = (long)data[start] + data[end] + data[mid];

                if (min > Math.abs(result)){
                    min = Math.abs(result);
                    ans1 = start;
                    ans2 = mid;
                    ans3 = end;
                }

                if (result < 0){
                    mid++;
                } else if (result == 0){
                    break loop1;
                } else {
                    end--;
                }
            }
        }

        System.out.println(data[ans1] + " " + data[ans2] + " " + data[ans3]);
    }
}
