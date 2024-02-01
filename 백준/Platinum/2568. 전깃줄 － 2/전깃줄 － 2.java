import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int[][] connection;
    static int[] lis;
    static int[] preIndex;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        connection = new int[N][2];
        int max = -1;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            connection[i][0] = a;
            connection[i][1] = b;

            max = Math.max(max, Math.max(a, b));
        }

        // b에 대해 오름차순 정렬
        Arrays.sort(connection, (c1, c2) -> c1[0] - c2[0]);
        lis = new int[N];
        preIndex = new int[N];

        int idx = 0;
        lis[0] = connection[0][1];
        for (int i = 1; i < N; i++) {
            if (lis[idx] < connection[i][1]){
                lis[idx + 1] = connection[i][1];
                idx++;
                preIndex[i] = idx;
            } else {
                int insertIdx = binarySearch(0, idx, connection[i][1]);
                lis[insertIdx] = connection[i][1];
                preIndex[i] = insertIdx;
            }
        }

        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        sb.append(N - (idx + 1)).append("\n");

        int index = idx;
        for (int i = N - 1; i >= 0; i--){
            if (preIndex[i] == index){
                set.add(connection[i][0]);
                index--;
            }
        }

        for(int i = 0; i < N; i++){
            if (!set.contains(connection[i][0])){
                sb.append(connection[i][0]).append("\n");
            }
        }

        System.out.print(sb);
    }

    public static int binarySearch(int left, int right, int key){
        while(left < right){
            int mid = (left + right) / 2;

            if(lis[mid] < key){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}