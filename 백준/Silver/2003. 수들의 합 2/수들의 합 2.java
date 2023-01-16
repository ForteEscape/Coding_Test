import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int[] data;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String line = br.readLine();
        st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        data = new int[N];

        line = br.readLine();
        st = new StringTokenizer(line);
        for(int i = 0; i<N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int start = 0;
        int end = 0;

        while(start < N && end < N){
            int result = sum(start, end);
            if(result == M){
                ans++;
                end++;
            }
            else if(result < M){
                end++;
            }
            else{
                start++;
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int sum(int start, int end){
        int result = 0;
        for(int i = start; i <= end; i++){
            result += data[i];
        }

        return result;
    }
}