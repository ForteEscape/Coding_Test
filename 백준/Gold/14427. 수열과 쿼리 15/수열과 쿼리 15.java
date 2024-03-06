import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] tree;
    static long[] arr;
    static int N, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new int[4 * N];
        arr = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(1, 1, N);

        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 2) {
                System.out.println(query(1, 1, N, 1, N));
            } else {
                int idx = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                update(1, 1, N, idx, val);
            }
        }
    }

    static void makeTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) / 2;
        makeTree(node * 2, start, mid);
        makeTree(node * 2 + 1, mid + 1, end);

        if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2];
        } else {
            tree[node] = tree[node * 2 + 1];
        }
    }

    static int query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return -1;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftMinIdx = query(node * 2, start, mid, left, right);
        int rightMinIdx = query(node * 2 + 1, mid + 1, end, left, right);

        if (leftMinIdx == -1) {
            return rightMinIdx;
        } else if (rightMinIdx == -1) {
            return leftMinIdx;
        }

        return arr[leftMinIdx] <= arr[rightMinIdx] ? leftMinIdx : rightMinIdx;
    }

    static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            arr[start] = val;
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

        if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2];
        } else {
            tree[node] = tree[node * 2 + 1];
        }
    }
}