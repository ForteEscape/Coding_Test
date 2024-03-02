import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            if (this.weight < o.weight) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    static int V, E;
    static int[] friends;
    static long[][] dist;
    static final long MAX = Long.MAX_VALUE;
    static List<Edge>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        friends = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        dist = new long[3][V + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= V; j++) {
                dist[i][j] = MAX;
            }
        }

        graph = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        E = Integer.parseInt(br.readLine());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < 3; i++) {
            dist[i][friends[i]] = 0;
            pq.offer(new Edge(friends[i], 0));

            while(!pq.isEmpty()) {
                Edge cur = pq.poll();

                if (dist[i][cur.to] > cur.weight) {
                    continue;
                }

                if (dist[i][cur.to] < cur.weight) {
                    continue;
                }

                for (Edge next : graph[cur.to]) {
                    if (dist[i][next.to] > cur.weight + next.weight) {
                        dist[i][next.to] = cur.weight + next.weight;
                        pq.offer(new Edge(next.to, dist[i][next.to]));
                    }
                }
            }
        }

        int ans = -1;
        long maxMinDist = Long.MIN_VALUE;
        for (int i = 1; i <= V; i++) {
            long minDist = Math.min(dist[0][i], Math.min(dist[1][i], dist[2][i]));

            if (maxMinDist < minDist) {
                maxMinDist = minDist;
                ans = i;
            }
        }

        System.out.println(ans);
    }
}