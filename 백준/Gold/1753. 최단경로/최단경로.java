import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int to;
        int weight;

        public Node(int to ,int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    final static int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        int[] dist = new int[V + 1];

        for (int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startNode, 0));
        dist[startNode] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for (int i = 0; i < graph.get(cur.to).size(); i++){
                Node adjNode = graph.get(cur.to).get(i);

                if (dist[adjNode.to] > cur.weight + adjNode.weight){
                    dist[adjNode.to] = cur.weight + adjNode.weight;
                    pq.offer(new Node(adjNode.to, dist[adjNode.to]));
                }
            }
        }

        for (int i = 1; i < V + 1; i++){
            if (dist[i] == INF){
                System.out.println("INF");
            } else{
                System.out.println(dist[i]);
            }
        }

    }
}