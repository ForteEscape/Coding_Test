import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long[] tree;
    static long[] arr;
    static int N, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);

            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());

            tree = new long[4 * N];
            arr = new long[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int element = Integer.parseInt(st.nextToken());
                arr[i] = Integer.compare(element, 0);
            }

            makeTree(1, 1, N);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());

                char type = st.nextToken().charAt(0);

                if (type == 'C') {
                    int idx = Integer.parseInt(st.nextToken());
                    int val = Integer.parseInt(st.nextToken());

                    val = Integer.compare(val, 0);

                    update(1, 1, N, idx, val);
                } else {
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());

                    long result = query(1, 1, N, left, right);

                    if (result == 0) {
                        sb.append(0);
                    } else if (result > 0) {
                        sb.append("+");
                    } else {
                        sb.append("-");
                    }
                }
            }

            System.out.println(sb);
        }
    }

    static void makeTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        makeTree(node * 2, start, mid);
        makeTree(node * 2 + 1, mid + 1, end);

        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 1;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(node * 2, start, mid, left, right) *
                query(node * 2 + 1, mid + 1, end, left, right);
    }

    static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }

        if (start > idx || idx > end) {
            return;
        }

        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(node * 2, start, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, end, idx, val);
        }

        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }
}