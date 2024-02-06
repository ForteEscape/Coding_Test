import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] input;
    static int[] tree;
    static int limit = 65537;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tree = new int[limit * 4];
        input = new int[N + 1];

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(br.readLine());
            updateTree(0, limit, 1, input[i], 1);

            if (i >= K) {
                int x = query(0, limit, 1, ((K + 1) / 2));
                ans += x;
                updateTree(0, limit, 1, input[i - K + 1], -1);
            }
        }

        System.out.println(ans);
    }

    public static int query(int left, int right, int node, int target) {
        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;

        if (target <= tree[node * 2]) {
            return query(left, mid, node * 2, target);
        } else {
            return query(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
        }
    }

    public static void updateTree(int start, int end, int node, int idx, int value) {
        if (idx < start || idx > end){
            return;
        }

        tree[node] += value;
        if (start == end) return;

        int mid = (start + end) / 2;
        updateTree(start, mid, node * 2, idx, value);
        updateTree(mid + 1, end, node * 2 + 1, idx, value);
    }
}