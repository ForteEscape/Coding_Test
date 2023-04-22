import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Vertex{
        int x;
        int y;
        int z;
        int idx;

        Vertex(int x, int y, int z, int idx){
            this.x = x;
            this.y = y;
            this.z = z;
            this.idx = idx;
        }
    }

    static class Edge{
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Edge> edges;
    static int[] parents;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Vertex[] vertices = new Vertex[N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            vertices[i] = new Vertex(x, y, z, i);
        }

        edges = new ArrayList<>();

        //x axis
        Arrays.sort(vertices, (x, y) -> (x.x - y.x));
        for (int i = 0; i < N - 1; i++){
            int weight = Math.abs(vertices[i].x - vertices[i + 1].x);
            edges.add(new Edge(vertices[i].idx, vertices[i + 1].idx, weight));
        }

        Arrays.sort(vertices, (x, y) -> (x.y - y.y));
        for (int i = 0; i < N - 1; i++){
            int weight = Math.abs(vertices[i].y - vertices[i + 1].y);
            edges.add(new Edge(vertices[i].idx, vertices[i + 1].idx, weight));
        }

        Arrays.sort(vertices, (x, y) -> (x.z - y.z));
        for (int i = 0; i < N - 1; i++){
            int weight = Math.abs(vertices[i].z - vertices[i + 1].z);
            edges.add(new Edge(vertices[i].idx, vertices[i + 1].idx, weight));
        }

        Collections.sort(edges, (x, y) -> (x.weight - y.weight));

        System.out.println(kruskal(N));
    }

    public static int kruskal(int V){
        parents = new int[V + 1];
        size = new int[V + 1];
        int weightSum = 0;
        int cnt = 0;

        for (int i = 1; i <= V; i++){
            parents[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < edges.size(); i++){
            if (cnt == V){
                return weightSum;
            }

            Edge edge = edges.get(i);

            if (find(edge.from) != find(edge.to)){
                union(edge.from, edge.to);
                weightSum += edge.weight;
            }
        }

        return weightSum;
    }

    static void union(int u, int v){
        int pu = find(u);
        int pv = find(v);

        if (size[pu] > size[pv]){
            parents[pv] = pu;
            size[pu] += size[pv];
        } else{
            parents[pu] = pv;
            size[pv] += size[pu];
        }
    }

    static int find(int v){
        if (v == parents[v]){
            return v;
        }

        return parents[v] = find(parents[v]);
    }
}