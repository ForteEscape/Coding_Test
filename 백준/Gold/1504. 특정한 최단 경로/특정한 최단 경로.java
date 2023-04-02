import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int to;
        int weight;

        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static int[] dist;
    static int V;
    static int E;
    static ArrayList<ArrayList<Node>> graph;
    final static int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int mid1 = Integer.parseInt(st.nextToken());
        int mid2 = Integer.parseInt(st.nextToken());

        int ans1 = dijkstra(1, mid1) + dijkstra(mid1, mid2) + dijkstra(mid2, V);
        int ans2 = dijkstra(1, mid2) + dijkstra(mid2, mid1) + dijkstra(mid1, V);

        if (ans1 >= INF && ans2 >= INF){
            System.out.println(-1);
        } else{
            System.out.println(Math.min(ans1, ans2));
        }
    }

    public static int dijkstra(int start, int end){
        dist = new int[V + 1];

        for (int i = 0; i < V + 1; i++){
            dist[i] = INF;
        }
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> (x.weight - y.weight));
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (dist[cur.to] < cur.weight) continue;

            for (int i = 0; i < graph.get(cur.to).size(); i++){
                Node adjNode = graph.get(cur.to).get(i);

                if (dist[adjNode.to] > cur.weight + adjNode.weight){
                    dist[adjNode.to] = cur.weight + adjNode.weight;
                    pq.offer(new Node(adjNode.to, dist[adjNode.to]));
                }
            }
        }

        return dist[end];
    }
}