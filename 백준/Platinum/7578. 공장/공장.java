import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr;
    static long[] tree;
    static Map<Integer, Integer> map;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        tree = new long[4 * N];
        arr = new int[N + 1];
        map = new TreeMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, i);
        }

        for (int i = 1; i <= N; i++) {
            ans += query(1, 1, N, map.get(arr[i]) + 1, N);
            update(1, 1, N, map.get(arr[i]), 1);
        }

        System.out.println(ans);
    }

    static long query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) +
                query(node * 2 + 1, mid + 1, end, left, right);
    }

    static void update(int node, int start, int end, int idx, int val) {
        if (start > idx || end < idx) {
            return;
        }

        tree[node] = tree[node] + val;

        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, val);
            update(node * 2 + 1, mid + 1, end, idx, val);
        }
    }
}