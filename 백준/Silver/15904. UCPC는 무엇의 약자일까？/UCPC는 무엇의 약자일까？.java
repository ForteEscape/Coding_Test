import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();

        char[] ucpc = {'U', 'C', 'P', 'C'};
        int idx = 0;
        
        for (int i = 0; i < data.length(); i++){
            if (ucpc[idx] == data.charAt(i)){
                idx++;
            }
            
            if (idx == 4){
                System.out.println("I love UCPC");
                return;
            }
        }

        System.out.println("I hate UCPC");
    }
}
