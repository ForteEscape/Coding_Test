import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    static int V, R;
    static List<Edge>[] graph;
    static int length1;
    static int gigaNode;
    static int length2;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        length1 = 0;
        gigaNode = 0;
        visited = new boolean[V + 1];

        graph[R].add(new Edge(0, 0));
        visited[0] = true;
        dfs(R, 0, 0);

        length2 = 0;
        dfs(gigaNode, 0, 1);

        System.out.println(length1 + " " + length2);
    }

    public static void dfs(int R, int length, int mod) {
        visited[R] = true;
        if (mod == 0) {
            if (graph[R].size() > 2 || (graph[R].size() == 1 && gigaNode == 0)) {
                gigaNode = R;
                length1 = length;
                return;
            }
        } else {
            if (graph[R].size() == 1) {
                length2 = Math.max(length2, length);
                return;
            }
        }

        for (int i = 0; i < graph[R].size(); i++) {
            Edge next = graph[R].get(i);

            if (!visited[next.to]){
                dfs(next.to, length + next.weight, mod);
            }
        }
    }
}