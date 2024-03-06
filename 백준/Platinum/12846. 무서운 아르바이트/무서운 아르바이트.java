import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] tree;
    static long[] arr;
    static int N;
    static long maxProfit;

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

        maxProfit = Long.MIN_VALUE;
        solve(1, N);

        System.out.println(maxProfit);
    }

    static void solve(int start, int end) {
        if (start > end) return;

        int minIdx = query(1, 1, N, start, end);
        long profit = (end - start + 1) * arr[minIdx];

        maxProfit = Math.max(maxProfit, profit);

        solve(start, minIdx - 1);
        solve(minIdx + 1, end);
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
}