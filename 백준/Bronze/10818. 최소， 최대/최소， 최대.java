import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] data = new int[N];
        st = new StringTokenizer(br.readLine());

        int idx = 0;

        while(st.hasMoreTokens()){
            data[idx++] = Integer.parseInt(st.nextToken());
        }

        int max = data[0];
        int min = data[0];

        for(int element: data){
            max = Math.max(max, element);
            min = Math.min(min, element);
        }
        
        System.out.println(min + " " + max);
    }
}