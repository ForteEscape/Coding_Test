import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            int l = Integer.parseInt(br.readLine());

            StringBuilder sb2 = new StringBuilder();
            for (int j = 0; j < l; j++) {
                sb2.append("=");
            }

            sb.append(sb2.toString()).append("\n");
        }
        System.out.print(sb);
    }
}