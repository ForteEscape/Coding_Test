import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static class Info implements Comparable<Info> {
        int idx;
        int val;

        Info(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Info o) {
            return this.val - o.val;
        }
    }

    static int[] tree;
    static int N;
    static Info[] data;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long ans = 0L;
        data = new Info[N + 1];
        tree = new int[4 * N];

        data[0] = new Info(-1, -1);
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            data[i] = new Info(i, num);
        }

        Arrays.sort(data);
        for (int i = 1; i <= N; i++) {
            ans += query(1, 1, N, data[i].idx + 1, N);
            update(1, 1, N, data[i].idx, 1);
        }

        System.out.println(ans);
    }

    static void update(int node, int start, int end, int idx, int val) {
        if (idx < start || idx > end) {
            return;
        }

        if (start == end) {
            tree[node] += val;
            return;
        }

        int mid = (start + end) >> 1;

        update(node << 1, start, mid, idx, val);
        update(node << 1 | 1, mid + 1, end, idx, val);

        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    static int query(int node, int start, int end, int left, int right) {
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