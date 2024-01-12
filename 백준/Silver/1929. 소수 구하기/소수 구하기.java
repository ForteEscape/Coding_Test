import java.io.*;
import java.util.*;

public class Main {

    public static int[] data;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        data = new int[M + 1];
        for (int i = 2; i <= M; i++){
            data[i] = i;
        }

        generatePrime(M);

        for (int i = N; i <= M; i++){
            if (data[i] != 0){
                System.out.println(i);
            }
        }
    }

    public static void generatePrime(int M){
        for (int i = 2; i <= Math.sqrt(M); i++){
            if (data[i] != 0){
                for (int j = i + i; j <= M; j += i){
                    data[j] = 0;
                }
            }
        }
    }
}
