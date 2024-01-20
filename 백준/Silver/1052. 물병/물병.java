import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int ans = 0;
        while(chk(N) > K){
            N += 1;
            ans++;
        }

        System.out.println(ans);
    }

    public static int chk(int N){
        int cnt = 0;

        for (char element: Integer.toBinaryString(N).toCharArray()){
            if (element == '1'){
                cnt++;
            }
        }

        return cnt;
    }
}