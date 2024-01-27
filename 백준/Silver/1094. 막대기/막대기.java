import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;

        for(int i = 0; i < 7; i++){
            if((N & (1 << i)) == (1 << i)){
                result++;
            }
        }

        System.out.println(result);
    }
}