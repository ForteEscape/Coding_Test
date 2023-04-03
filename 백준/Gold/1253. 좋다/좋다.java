import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> data = new LinkedList<>();
        ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            data.addLast(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(data);

        for (int i = 0; i < N; i++){
            LinkedList<Integer> dataSet = new LinkedList<>(data);
            int key = dataSet.get(i);
            dataSet.remove(i);

            if(solve(dataSet, key)) ans++;
        }

        System.out.println(ans);
    }

    public static boolean solve(LinkedList<Integer> dataSet, int key){
        int start = 0;
        int end = dataSet.size() - 1;

        while(start < end){
            int sum = dataSet.get(start) + dataSet.get(end);

            if (sum >= key){
                if (sum == key){
                    return true;
                }

                end--;
            } else{
                start++;
            }
        }

        return false;
    }
}