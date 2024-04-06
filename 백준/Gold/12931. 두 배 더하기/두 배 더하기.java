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

        int ans = 0;
        while(true) {
            boolean isOdd = false;
            if(chkIsZero()) {
                break;
            }

            for(int i = 0; i < N; i++) {
                if(data[i] % 2 != 0) {
                    data[i]--;
                    isOdd = true;
                    ans++;
                }
            }

            if (!isOdd) {
                for (int i = 0; i < N; i++) {
                    data[i] /= 2;
                }
                ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean chkIsZero() {
        for(int i = 0; i < data.length; i++) {
            if (data[i] != 0) {
                return false;
            }
        }

        return true;
    }
}