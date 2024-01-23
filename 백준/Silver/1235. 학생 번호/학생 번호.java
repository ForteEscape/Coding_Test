import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] data = new String[N];
        for (int i = 0; i < N; i++){
            data[i] = br.readLine();
        }

        if (data[0].length() == 1){
            System.out.println(1);
            return;
        }

        for (int i = 1; i < data[0].length(); i++){
            Set<String> set = new HashSet<>();
            for (int j = 0; j < N; j++){
                String result = data[j].substring(data[j].length() - i);

                if (set.contains(result)){
                    break;
                }
                set.add(result);
            }

            if (set.size() == N){
                System.out.println(i);
                return;
            }
        }

        System.out.println(data[0].length());
    }
}