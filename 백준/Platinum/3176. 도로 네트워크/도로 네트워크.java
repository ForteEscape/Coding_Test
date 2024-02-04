import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, K, M;
    static int max, min;
    static int[][] parent;
    static int[][] minDist;
    static int[][] maxDist;
    static int[] depth;
    static List[] tree;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        K = -1;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        tree = new List[N + 1];
        parent = new int[K + 1][N + 1];
        depth = new int[N + 1];

        minDist = new int[K + 1][N + 1];
        maxDist = new int[K + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[from].add(new Node(to, weight));
            tree[to].add(new Node(from, weight));
        }

        bfs(1);

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];

                minDist[i][j] = Math.min(minDist[i - 1][j], minDist[i - 1][parent[i - 1][j]]);
                maxDist[i][j] = Math.max(maxDist[i - 1][j], maxDist[i - 1][parent[i - 1][j]]);
            }
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            LCA(a,b);

            sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }

    public static void LCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        min = Integer.MAX_VALUE;
        max = -1;

        int diff = depth[b] - depth[a];
        for (int i = K; i >= 0; i--) {
            if ((diff & (1 << i)) == (1 << i)) {
                min = Math.min(min, minDist[i][b]);
                max = Math.max(max, maxDist[i][b]);

                b = parent[i][b];
            }
        }

        if (a == b) {
            return;
        }

        for (int i = K; i >= 0; i--){
            if (parent[i][a] != parent[i][b]) {
                min = Math.min(min, Math.min(minDist[i][a], minDist[i][b]));
                max = Math.max(max, Math.max(maxDist[i][a], maxDist[i][b]));

                a = parent[i][a];
                b = parent[i][b];
            }
        }

        min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
        max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));
    }

    public static void bfs(int startNode) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(new Node(startNode, 0));
        depth[startNode] = 1;

        while(!queue.isEmpty()) {
            Node cur = queue.pollFirst();

            for (int i = 0; i < tree[cur.to].size(); i++) {
                Node adjNode = (Node) tree[cur.to].get(i);

                if (depth[adjNode.to] == 0) {
                    depth[adjNode.to] = depth[cur.to] + 1;
                    parent[0][adjNode.to] = cur.to;

                    minDist[0][adjNode.to] = adjNode.weight;
                    maxDist[0][adjNode.to] = adjNode.weight;

                    queue.addLast(adjNode);
                }
            }
        }
    }
}