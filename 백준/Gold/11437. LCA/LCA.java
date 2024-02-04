import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] depth;
    static int[][] parent;
    static List<List<Integer>> tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = 20;

        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        int a,b;
        for (int i = 1; i <= N-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        bfs(1);

        for (int i = 1; i<=K; i++) {
            for (int j = 1; j<=N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        // 4. LCA 진행
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int startNode) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(startNode);
        depth[startNode] = 1;

        while(!queue.isEmpty()) {
            int cur = queue.pollFirst();

            for(int i = 0; i < tree.get(cur).size(); i++) {
                int adjNode = tree.get(cur).get(i);
                if (depth[adjNode] == 0) {
                    queue.addLast(adjNode);
                    depth[adjNode] = depth[cur] + 1;
                    parent[0][adjNode] = cur;
                }
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[b] < depth[a]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = depth[b] - depth[a];
        for (int i = K; i>=0; i--) {
            if ((diff & (1 << i)) == (1 << i)) {
                b = parent[i][b];
            }
        }

        if (a == b) return a;

        for (int i = K; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}