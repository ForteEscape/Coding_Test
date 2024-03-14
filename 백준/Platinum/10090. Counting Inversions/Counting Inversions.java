import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] tree;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new int[4 * N];
        long ans = 0L;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            ans += query(1, 1, N, num + 1, N);
            update(1, 1, N, num, 1);
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

    static long query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;

        return query(node << 1, start, mid, left, right) +
                query(node << 1 | 1, mid + 1, end, left, right);
    }
}