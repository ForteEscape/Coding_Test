import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[] data = {'K', 'S', 'A'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int idx = 0;
        int ans = 0;
        int ksaStringIdx = 0;

        if (line.length() == 1) {
            if(line.charAt(0) == 'K') {
                System.out.println(0);
            } else {
                System.out.println(2);
            }

            return;
        }

        while(line.charAt(idx) != data[ksaStringIdx % 3]) {
            ksaStringIdx++;
            ans++;
        }
        //System.out.println("ans : " + ans);
        idx++;
        ksaStringIdx++;

        while(idx < line.length()) {
            if (line.charAt(idx) != data[ksaStringIdx % 3]) {
                idx++;
                ans++;
                continue;
            }

            idx++;
            ksaStringIdx++;
        }

        ans += Math.abs(line.length() - ksaStringIdx);
        //System.out.println("ans : " + ans);
        int ans2 = 0;

        idx = 0;
        ksaStringIdx = 0;
        while(idx < line.length()) {
            if(line.charAt(idx) != data[ksaStringIdx % 3]) {
                idx++;
                ans2++;
                continue;
            }

            idx++;
            ksaStringIdx++;
        }

        ans2 += Math.abs(idx - ksaStringIdx);
        //System.out.println("ans2 : " + ans2);

        //System.out.println(Math.min(ans, ans2));

        int ans3 = 0;
        idx = 0;
        ksaStringIdx = 0;
        while(idx < line.length() && line.charAt(idx) != data[ksaStringIdx % 3]) {
            idx++;
            ksaStringIdx++;
            ans3 += 2;
        }
        //System.out.println("ans3 : " + ans3);
        idx++;
        ksaStringIdx++;

        while(idx < line.length()) {
            if (line.charAt(idx) != data[ksaStringIdx % 3]) {
                idx++;
                ans3++;
                continue;
            }

            idx++;
            ksaStringIdx++;
        }

        ans3 += Math.abs(idx - ksaStringIdx);
        //System.out.println("ans3 : " + ans3);

        System.out.println(Math.min(ans, Math.min(ans2, ans3)));
    }
}