import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] depth;
    static int[][] parent;
    
    static ArrayList[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        K = -1;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];

        tree = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        int a,b;
        for (int i = 1; i <= N-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 1);

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

    static void dfs(int id, int cnt) {
        depth[id] = cnt;

        for (int i = 0; i < tree[id].size(); i++) {
            int next = (Integer)tree[id].get(i);

            if (depth[next] == 0) {
                dfs(next, cnt+1);
                parent[0][next] = id;
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = K; i>=0; i--) {
            if ((1 << i) <= depth[a] - depth[b]) {
                a = parent[i][a];
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