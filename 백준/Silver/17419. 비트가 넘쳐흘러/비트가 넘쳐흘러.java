import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String data = br.readLine();

        int ans = 0;
        for (int i = 0; i < data.length(); i++){
            if (data.charAt(i) == '1'){
                ans++;
            }
        }

        System.out.println(ans);
    }
}