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
    }

    static int[][] dist;
    static List[] graph;
    static final int INF = Integer.MAX_VALUE;
    static int N, V, E;
    static int kist, crfood;
    static int[] location;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[2][V + 1];
        graph = new List[V + 1];
        location = new int[N];

        for (int i = 1; i <= V; i++) {
            dist[0][i] = INF;
            dist[1][i] = INF;
            graph[i] = new ArrayList<Edge>();
        }

        st = new StringTokenizer(br.readLine());
        kist = Integer.parseInt(st.nextToken());
        crfood = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            location[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        dijkstra(kist);
        dijkstra(crfood);

        int ans = 0;
        int kistData = 0;
        int crfoodData = 0;
        for (int element : location) {
            if (dist[0][element] == INF) {
                ans += -1;
                kistData++;
            } else {
                ans += dist[0][element];
            }

            if (dist[1][element] == INF) {
                ans += -1;
                crfoodData++;
            } else {
                ans += dist[1][element];
            }
        }

        if (kistData == N || crfoodData == N) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static void dijkstra(int startNode) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        int idx = startNode == kist ? 0 : 1;
        dist[idx][startNode] = 0;

        pq.offer(new Edge(startNode, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[idx][cur.to] < cur.weight) {
                continue;
            }

            for (int i = 0; i < graph[cur.to].size(); i++) {
                Edge next = (Edge) graph[cur.to].get(i);

                if (dist[idx][next.to] > cur.weight + next.weight) {
                    dist[idx][next.to] = cur.weight + next.weight;

                    pq.offer(new Edge(next.to, dist[idx][next.to]));
                }
            }
        }
    }
}