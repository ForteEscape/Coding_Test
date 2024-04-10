import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2=  br.readLine();

        int len1 = str1.length();
        int len2 = str2.length();

        dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[len1][len2]);

        Deque<Character> stack = new ArrayDeque<>();

        while(len1 > 0 && len2 > 0) {
            if (dp[len1][len2] == dp[len1 - 1][len2]) {
                len1--;
            } else if (dp[len1][len2] == dp[len1][len2 - 1]) {
                len2--;
            } else {
                stack.addLast(str1.charAt(len1 - 1));
                len1--;
                len2--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        System.out.println(sb);
    }
}