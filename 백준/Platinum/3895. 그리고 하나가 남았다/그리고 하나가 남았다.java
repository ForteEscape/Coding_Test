import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] data;
    static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && K == 0 && M == 0) {
                break;
            }

            data = new int[N + 1];
            tree = new int[4 * N];

            for (int i = 1; i <= N; i++) {
                data[i] = 1;
            }

            makeTree(1, 1, N);

            int sum = 0;
            int next = 0;
            int idx = 0;
            for(int i = 0; i < N; i++) {
                if (i == 0) {
                    idx = getKth(1, 1, N, M);

                    sum = query(1, 1, N, 1, idx - 1);
                    update(1, 1, N, idx, 0);
                    continue;
                }

                int total = query(1, 1, N, 1, N);
                next = (sum + K) % total;
                if(next == 0) {
                    next = total;
                }

                idx = getKth(1, 1, N, next);

                if(i == N - 1) {
                    sb.append(idx).append("\n");
                    break;
                }
                sum = query(1, 1, N, 1, idx - 1);
                update(1, 1, N, idx, 0);
            }
        }
        System.out.print(sb);
    }

    static void makeTree(int node, int start, int end) {
        if(start == end) {
            tree[node] = 1;
            return;
        }

        int mid = (start + end) >> 1;

        makeTree(node << 1, start, mid);
        makeTree(node << 1 | 1, mid + 1, end);

        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    static int getKth(int node, int start, int end, int idx) {
        if (start == end) {
            return start;
        }

        int mid = (start + end) >> 1;

        if (tree[node << 1] >= idx) {
            return getKth(node << 1, start, mid, idx);
        } else {
            return getKth(node << 1 | 1, mid + 1, end, idx - tree[node << 1]);
        }
    }

    static void update(int node, int start, int end, int idx, int val) {
        if(idx < start || end < idx) {
            return;
        }

        if(start == end) {
            tree[node] = val;
            return;
        }

        int mid = (start + end) >> 1;
        if(idx <= mid) {
            update(node << 1, start, mid, idx, val);
        } else {
            update(node << 1 | 1, mid + 1, end, idx, val);
        }

        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    static int query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }

        if(start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;

        return query(node << 1, start, mid, left, right) +
                query(node << 1 | 1, mid + 1, end, left, right);
    }
}