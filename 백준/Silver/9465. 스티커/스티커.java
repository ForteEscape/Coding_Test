import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++){
            int N = Integer.parseInt(br.readLine());

            int[][] data = new int[2][N];

            for (int i = 0; i < 2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (N > 1){
                data[0][1] += data[1][0];
                data[1][1] += data[0][0];
            }

            for (int i = 2; i < N; i++){
                data[0][i] += Math.max(data[1][i - 1], data[1][i - 2]);
                data[1][i] += Math.max(data[0][i - 1], data[0][i - 2]);
            }

            System.out.println(Math.max(data[0][N - 1], data[1][N - 1]));
        }
    }
}

