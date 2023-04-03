import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);

        for (int i = 0; i < N; i++){
            int key = data[i];

            if(solve(data, key, i)) ans++;
        }

        System.out.println(ans);
    }

    public static boolean solve(int[] dataSet, int key, int keyIdx){
        int start = 0;
        int end = dataSet.length - 1;

        while(start < end){
            int sum = dataSet[start] + dataSet[end];

            if (start == keyIdx){
                start++;
                continue;
            }

            if (end == keyIdx){
                end--;
                continue;
            }

            if (sum > key){
                end--;
            } else if (sum == key){
                return true;
            } else{
                start++;
            }
        }

        return false;
    }
}