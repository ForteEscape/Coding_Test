import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i< N; i++) {
            String data =br.readLine();

            System.out.println(data.length() >= 6 && data.length() <= 9 ? "yes" : "no");
        }
    }
}