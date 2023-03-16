import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dataList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dataList = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            dataList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(dataList);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++){
            System.out.print(isExists(Integer.parseInt(st.nextToken())) + " ");
        }
    }

    static int isExists(int key){
        int left = 0;
        int right = dataList.length - 1;

        while(left <= right){
            int mid = (left + right) / 2;

            if (dataList[mid] == key){
                return 1;
            } else if (dataList[mid] < key){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        return 0;
    }
}
