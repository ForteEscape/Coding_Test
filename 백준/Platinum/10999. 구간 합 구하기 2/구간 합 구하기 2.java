import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long[] arr, tree, lazy;
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[4 * N];
        lazy = new long[4 * N];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        makeTree(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int opt = Integer.parseInt(st.nextToken());
            if (opt == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());

                updateRange(1, 1, N, left, right, val);
            } else if (opt == 2){
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                sb.append(query(1, 1, N, left, right)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void makeTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) >> 1;

        makeTree(node << 1, start, mid);
        makeTree(node << 1 | 1, mid + 1, end);

        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    // propagation
    static void lazyUpdate(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += ((long)end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node << 1] += lazy[node];
                lazy[node << 1 | 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    static void updateRange(int node, int start, int end, int left, int right, long val) {
        lazyUpdate(node, start, end);

        if (left > end || right < start) {
            return;
        }

        if (start >= left && end <= right) {
            tree[node] += ((long)(end - start + 1) * val);

            if (start != end) {
                lazy[node << 1] += val;
                lazy[node << 1 | 1] += val;
            }
            return;
        }

        int mid = (start + end) >> 1;
        updateRange(node << 1, start, mid, left, right, val);
        updateRange(node << 1 | 1, mid + 1, end, left, right, val);

        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        lazyUpdate(node, start, end);

        if (left > end || right < start) {
            return 0;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        return query(node << 1, start, mid, left, right) +
                query(node << 1 | 1, mid + 1, end, left, right);
    }
}