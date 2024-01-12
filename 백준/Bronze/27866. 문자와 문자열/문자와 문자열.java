import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();
        int K = Integer.parseInt(br.readLine());

        System.out.println(data.charAt(K - 1));
    }
}
