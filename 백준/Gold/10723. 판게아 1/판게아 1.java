import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int from;
        int to;
        long weight;

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    static int V, N, T;
    static int[] parent;
    static int[] size;
    static List<Edge> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();

            for (int i = 1; i < V; i++) {
                st = new StringTokenizer(br.readLine());

                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges.add(new Edge(i, to, weight));
            }

            List<Long> answer = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                makeSet();
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                if (from != to) {
                    edges.add(new Edge(from, to, weight));
                }

                answer.add(kruskal());
            }

            long ans = answer.get(0);
            for (int i = 1; i < answer.size(); i++) {
                ans = ans ^ answer.get(i);
            }
            System.out.println(ans);
        }
    }

    public static void makeSet(){
        parent = new int[2001];
        size = new int[2001];

        for (int i = 0; i <= V; i++) {
            parent[i] = i;
            size[i] = 0;
        }
    }

    public static long kruskal() {
        Collections.sort(edges, Comparator.comparingLong(e -> e.weight));
        
        long result = 0L;
        int cnt = 0;

        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                result += edge.weight;
                cnt++;
            }

            if (cnt == V - 1) {
                break;
            }
        }

        return result;
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