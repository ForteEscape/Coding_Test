import java.io.*;
import java.util.*;

public class Main {

    static int[][] parent;
    static int[] depth;
    static int N, M, K;
    static List[] tree;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        K = -1;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        parent = new int[K + 1][N + 1];
        depth = new int[N + 1];
        tree = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        bfs(1);

        for (int i = 1; i <= K; i++){
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());
        int ans = 0;
        int start = 1;
        for (int i = 0; i < M; i++) {
            int end = Integer.parseInt(br.readLine());

            int common = LCA(start, end);
            ans += depth[start] + depth[end] - (2 * depth[common]);
            start = end;
        }

        System.out.println(ans);
    }

    public static int LCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[b] - depth[a];
        for (int i = K; i >= 0; i--) {
            if ((diff & (1 << i)) == (1 << i)) {
                b = parent[i][b];
            }
        }

        if (a == b){
            return a;
        }

        for (int i = K; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    public static void bfs(int startNode) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(startNode);
        depth[startNode] = 1;

        while(!queue.isEmpty()) {
            int cur = queue.pollFirst();

            for (int i = 0; i < tree[cur].size(); i++) {
                int adjNode = (Integer) tree[cur].get(i);

                if (depth[adjNode] == 0) {
                    depth[adjNode] = depth[cur] + 1;
                    parent[0][adjNode] = cur;

                    queue.addLast(adjNode);
                }
            }
        }
    }
}