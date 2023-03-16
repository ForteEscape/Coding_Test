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
        int A = Integer.parseInt(st.nextToken());

        int H = Integer.parseInt(br.readLine());

        long ans = 1L;

        for(int i = 0; i < N - 1; i++){
            ans = (ans * M) % 1000000007;
        }

        System.out.println(ans);
    }
}