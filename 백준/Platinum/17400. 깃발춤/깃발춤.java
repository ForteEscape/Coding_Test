import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long[] tree;
    static long[] arr;
    static int N, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[4 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (i % 2 == 0) {
                arr[i] = -arr[i];
            }
        }

        makeTree(1, 1, N);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int option = Integer.parseInt(st.nextToken());
            if (option == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                System.out.println(Math.abs(query(1, 1, N, left, right)));
            } else {
                int idx = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                if (idx % 2 == 0) {
                    val = -val;
                }

                update(1, 1, N, idx, val);
                arr[idx] += val;
            }
        }
    }

    public static void makeTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        makeTree(node * 2, start, mid);
        makeTree(node * 2 + 1, mid + 1, end);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static long query(int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 0;
        }

        if(start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(node * 2, start, mid, left, right) +
                query(node * 2 + 1, mid + 1, end, left, right);
    }

    public static void update(int node, int start, int end, int idx, int val) {
        if (end < idx || idx < start) {
            return;
        }

        tree[node] += val;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, val);
        update(node * 2 + 1, mid + 1, end, idx, val);
    }
}