import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();
        int ans = data.length();

        for (int i = 0; i < data.length(); i++){
            if (isCorrect(data.substring(i))){
                break;
            }
            ans++;
        }

        System.out.println(ans);
    }

    public static boolean isCorrect(String s){
        int start = 0;
        int end = s.length() - 1;

        while(start <= end){
            if (s.charAt(start) != s.charAt(end)){
                return false;
            }

            start++;
            end--;
        }

        return true;
    }
}