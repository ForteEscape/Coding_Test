import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long A = Long.parseLong(br.readLine());
        long Y = Long.parseLong(br.readLine());

        System.out.println(pow(A, Y) % MOD);
    }

    static long pow(long x, long y) {
        if(y == 0) {
            return 1;
        }

        long res = pow(x, y / 2);

        if(y % 2 == 0) {
            return ((res % MOD) * (res % MOD)) % MOD;
        } else {
            return ((((res % MOD) * (res % MOD)) % MOD) * (x % MOD)) % MOD;
        }
    }

}