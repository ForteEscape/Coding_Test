import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int V, E;
    static int[] parent;
    static int[] size;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        size = new int[V + 1];
        edges = new Edge[E];

        for (int i = 0; i <= V; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int startWeight = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));
        int worst = mst();

        for (int i = 0; i <= V; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        Arrays.sort(edges, Comparator.comparing(e -> -e.weight));
        int best = mst();

        if (startWeight == 0) {
            best++;
            worst++;
        }

        System.out.println((worst * worst) - (best * best));
    }

    public static int mst(){
        int cnt = 0;
        int ans = 0;

        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                cnt++;
                ans += edge.weight == 0 ? 1 : 0;
                union(edge.from, edge.to);
            }

            if (cnt == V - 1) {
                break;
            }
        }

        return ans;
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (size[pa] >= size[pb]) {
            parent[pb] = pa;
            size[pa] += size[pb];
        } else {
            parent[pa] = pb;
            size[pb] += size[pa];
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }
}