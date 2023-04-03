import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++){
            String data = br.readLine();

            System.out.println(solve(data, 0, data.length() - 1, 0));
        }
    }

    public static int solve(String data, int start, int end, int chance){
        while(start < end){
            if (data.charAt(start) == data.charAt(end)){
                start++;
                end--;
            } else{
                if (chance == 1){
                    return 2;
                }

                chance++;
                int res = solve(data, start, end - 1, chance);
                
                if (res == 1){
                    end--;
                    continue;
                }

                res = solve(data, start + 1, end, chance);
                
                if (res == 1){
                    start++;
                }
            }
        }

        return chance;
    }
}