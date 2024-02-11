import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String temp1 = br.readLine();
        String temp2 = br.readLine();

        char[][] result = new char[2][N * 2 + M];

        int T = Integer.parseInt(br.readLine());

        T = Math.min(T, N + M);
        for (int i = T, idx = temp1.length() - 1; i < T + temp1.length(); i++, idx--) {
            result[0][i] = temp1.charAt(idx);
        }

        for (int i = temp1.length(), idx = 0; i < temp1.length() + temp2.length(); i++, idx++) {
            result[1][i] = temp2.charAt(idx);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result[0].length; i++) {
            if(result[1][i] != '\0') {
                sb.append(result[1][i]);
            }

            if(result[0][i] != '\0') {
                sb.append(result[0][i]);
            }
        }

        System.out.println(sb);
    }
}