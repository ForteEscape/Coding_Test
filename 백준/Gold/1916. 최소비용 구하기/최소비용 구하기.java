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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        int[] dist = new int[V + 1];
        for (int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }

        StringTokenizer st;
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int goalNode = Integer.parseInt(st.nextToken());

        dist[startNode] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        pq.offer(new Node(startNode, 0));

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

        System.out.println(dist[goalNode]);
    }
}