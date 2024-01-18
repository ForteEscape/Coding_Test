import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree {
        static int treeSize;
        static long[] tree;
        static final int MOD = 1000000007;

        public SegmentTree(int height){
            treeSize = 1 << (height + 1);
            tree = new long[treeSize];
        }

        public long init(int[] arr, int node, int start, int end){
            if (start == end){
                return tree[node] = arr[start];
            }

            long left = init(arr, node * 2, start, (start + end) / 2);
            long right = init(arr, node * 2 + 1, (start + end) / 2 + 1, end);

            return tree[node] = (left * right) % MOD;
        }

        public long query(int node, int start, int end, int left, int right){
            if (left > end || right < start){
                return 1;
            }

            if (left <= start && end <= right){
                return tree[node];
            }

            return (query(node * 2, start, (start + end) / 2, left, right) *
                    query(node * 2 + 1, (start + end) / 2 + 1, end, left, right)) % MOD;
        }

        public long update(int node, int start, int end, int idx, long val){
            if (idx < start || idx > end){
                return tree[node];
            }

            if (start == end){
                return tree[node] = val;
            }

            return tree[node] = (update(node * 2, start, (start + end) / 2, idx, val) *
                    update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val)) % MOD;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] data = new int[N + 1];
        int height = (int)Math.ceil(Math.log(N) / Math.log(2));

        SegmentTree segTree = new SegmentTree(height);

        for (int i = 1; i <= N; i++){
            data[i] = Integer.parseInt(br.readLine());
        }

        segTree.init(data, 1, 1, N);
        for (int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (cmd == 1){
                segTree.update(1, 1, N, start, end);
            } else {
                System.out.println(segTree.query(1, 1, N, start, end));
            }
        }
    }
}

