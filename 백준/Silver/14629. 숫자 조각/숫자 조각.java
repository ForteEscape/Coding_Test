import java.util.*;
import java.io.*;

public class Main {

    static boolean[] visited;
    static long gap = Long.MAX_VALUE;
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (Long.parseLong(line) >= 9876543210L){
            System.out.println(9876543210L);
            return;
        }

        visited = new boolean[10];
        ans = -1;

        solve(line, 0, "");

        System.out.println(ans);
    }

    public static void solve(String line, int length, String current){
        if (line.length() == length){
            long N = Long.parseLong(line);
            long result = Long.parseLong(current);

            if (Math.abs(N - result) < gap){
                ans = result;
                gap = Math.abs(N - result);
            }

            return;
        }

        for (int i = 0; i < 10; i++){
            if (!visited[i]){
                visited[i] = true;
                current += String.valueOf(i);

                solve(line, length + 1, current);

                current = current.substring(0, current.length() - 1);
                visited[i] = false;
            }
        }
    }
}