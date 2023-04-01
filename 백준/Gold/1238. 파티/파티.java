import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int to;
        int weight;

        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        dist = new int[V + 1];

        for (int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        int[] result = new int[V + 1];

        for (int i = 1; i < V + 1; i++){
            initDist(V + 1);
            dist[i] = 0;

            dijkstra(i);

            if (i == startNode){
                for (int j = 1; j < V + 1; j++){
                    result[j] += dist[j];
                }
            } else{
                result[i] += dist[startNode];
            }
        }

        int ans = -1;
        for (int i = 1; i < V + 1; i++){
            ans = Math.max(ans, result[i]);
        }

        System.out.println(ans);
    }

    public static void initDist(int length){
        for (int i = 0; i < length; i++){
            dist[i] = INF;
        }
    }

    public static void dijkstra(int startNode){
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> (x.weight - y.weight));
        pq.offer(new Node(startNode, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (dist[cur.to] < cur.weight){
                continue;
            }

            for (int i = 0; i < graph.get(cur.to).size(); i++){
                Node adjNode = graph.get(cur.to).get(i);

                if (dist[adjNode.to] > cur.weight + adjNode.weight){
                    dist[adjNode.to] = cur.weight + adjNode.weight;
                    pq.offer(new Node(adjNode.to, dist[adjNode.to]));
                }
            }
        }
    }
}