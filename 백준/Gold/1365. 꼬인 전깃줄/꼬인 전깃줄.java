import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] lis;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        lis = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];
        int idx = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i] > lis[idx]) {
                lis[++idx] = arr[i];
            } else {
                int tempIdx = binarySearch(idx, arr[i]);
                lis[tempIdx] = arr[i];
            }
        }

        System.out.println(N - (idx + 1));
    }

    static int binarySearch(int idx, int key) {
        int left = 0;
        int right = idx;

        while(left < right) {
            int mid = (left + right) / 2;

            if (lis[mid] > key) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }
}