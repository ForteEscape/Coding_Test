import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int[] arr;
    static int[] tree;
    static int N;
    static long maxArea;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        tree = new int[4 * N];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        makeTree(1, 1, N);

        maxArea = Long.MIN_VALUE;
        solve(1, N);

        System.out.println(maxArea);
    }

    static void solve(int start, int end) {
        int minIdx = query(1, 1, N, start, end);
        long width = (end - start + 1);

        long area = width * arr[minIdx];

        maxArea = Math.max(maxArea, area);

        if (minIdx + 1 <= end) {
            solve(minIdx + 1, end);
        }

        if (minIdx - 1 >= start) {
            solve(start, minIdx - 1);
        }
    }

    static void makeTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) >> 1;

        makeTree(node << 1, start, mid);
        makeTree(node << 1 | 1, mid + 1, end);

        tree[node] = arr[tree[node << 1]] <= arr[tree[node << 1 | 1]] ? tree[node << 1] : tree[node << 1 | 1];
    }

    static int query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return -1;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int leftMinIdx = query(node << 1, start, mid, left, right);
        int rightMinIdx = query(node << 1 | 1, mid + 1, end, left, right);

        if (leftMinIdx == -1) {
            return rightMinIdx;
        } else if (rightMinIdx == -1) {
            return leftMinIdx;
        }

        return arr[leftMinIdx] <= arr[rightMinIdx] ? leftMinIdx : rightMinIdx;
    }
}