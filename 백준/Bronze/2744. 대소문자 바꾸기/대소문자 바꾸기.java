import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringBuilder sb = new StringBuilder();
        for(char element: line.toCharArray()){
            if (Character.isUpperCase(element)){
                sb.append(Character.toLowerCase(element));
            } else {
                sb.append(Character.toUpperCase(element));
            }
        }

        System.out.println(sb);
    }
}