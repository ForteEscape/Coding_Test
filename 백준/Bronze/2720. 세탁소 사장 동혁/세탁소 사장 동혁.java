import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] coin = {25, 10, 5, 1};

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int idx = 0;

            StringBuilder sb = new StringBuilder();
            while(idx < 4) {
                sb.append(N / coin[idx]).append(" ");
                N -= coin[idx] * (N / coin[idx]);
                idx++;
            }

            System.out.println(sb);
        }
    }
}