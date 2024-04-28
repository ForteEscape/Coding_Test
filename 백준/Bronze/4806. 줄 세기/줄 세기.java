import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int cnt = 0;
        
        while((line = br.readLine()) != null) {
            cnt++;
        }

        System.out.println(cnt);
    }
}