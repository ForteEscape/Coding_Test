import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static long L;
    static int[] data;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new int[N];
        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(data);
        int ans = 0;

        for(int i = 0; i < N; i++) {
            if(data[i] <= L) {
                ans++;
                L += K;
            } else {
                break;
            }
        }

        System.out.println(ans);
    }

}