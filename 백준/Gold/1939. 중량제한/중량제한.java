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

    static int[] parent;
    static int[] size;
    static int V, E;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        size = new int[V + 1];
        edges = new Edge[E];

        for (int i = 1; i <= V; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> -e.weight));

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        for (Edge edge : edges) {
            union(edge.from, edge.to);

            if (find(start) == find(to)) {
                System.out.println(edge.weight);
                break;
            }
        }
    }

    static void union(int a, int b) {
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

    static int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }
}