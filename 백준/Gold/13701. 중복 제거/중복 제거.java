import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BitSet bitSet = new BitSet(33554432);
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()){
            int element = Integer.parseInt(st.nextToken());

            if (!bitSet.get(element)){
                bitSet.set(element);
                sb.append(element).append(" ");
            }
        }

        System.out.println(sb);
    }
}