import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;
        int time;

        Edge(int from, int to, int weight, int time) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.weight == o.weight) {
                return this.time - o.time;
            }
            return this.weight - o.weight;
        }
    }

    static int[] parents;
    static int[] size;
    static Edge[] edges;
    static int V, E;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        size = new int[V + 1];
        edges = new Edge[E];

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight, time);
        }

        Arrays.sort(edges);

        long totalWeight = 0L;
        int time = Integer.MIN_VALUE;
        int cnt = 0;

        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalWeight += edge.weight;
                time = Math.max(time, edge.time);
                cnt++;
            }

            if (cnt == V - 1) {
                break;
            }
        }

        if (cnt == V - 1) {
            System.out.println(time + " " + totalWeight);
        } else {
            System.out.println(-1);
        }
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (size[pa] >= size[pb]) {
            parents[pb] = pa;
            size[pa] += size[pb];
        } else {
            parents[pa] = pb;
            size[pb] += size[pa];
        }
    }

    public static int find(int x) {
        if (x == parents[x]) {
            return parents[x];
        }

        return parents[x] = find(parents[x]);
    }
}