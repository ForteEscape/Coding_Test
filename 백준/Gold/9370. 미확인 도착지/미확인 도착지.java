import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge{
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<ArrayList<Edge>> graph;
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++){
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] dist1 = new int[V + 1];
            int[] dist2 = new int[V + 1];
            graph = new ArrayList<>();

            for (int i = 0; i < V + 1; i++){
                graph.add(new ArrayList<>());
                dist1[i] = INF;
                dist2[i] = INF;
            }

            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int crossNode1 = Integer.parseInt(st.nextToken());
            int crossNode2 = Integer.parseInt(st.nextToken());

            // graph construction
            for (int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Edge(to, weight));
                graph.get(to).add(new Edge(from, weight));
            }

            int[] candidate = new int[K];
            for (int i = 0; i < K; i++){
                candidate[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(candidate);

            dijkstra(startNode, dist1);

            int newStartNode = dist1[crossNode1] > dist1[crossNode2] ? crossNode1 : crossNode2;

            dijkstra(newStartNode, dist2);

            for(int element: candidate){
                if (dist1[element] == dist1[newStartNode] + dist2[element]){
                    System.out.print(element + " ");
                }
            }
            System.out.println();
        }
    }

    static void dijkstra(int startNode, int[] dist){
        dist[startNode] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> (x.weight - y.weight));

        pq.offer(new Edge(startNode, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if (dist[cur.to] < cur.weight){
                continue;
            }

            for (int i = 0; i < graph.get(cur.to).size(); i++){
                Edge adjEdge = graph.get(cur.to).get(i);

                if (dist[adjEdge.to] > cur.weight + adjEdge.weight){
                    dist[adjEdge.to] = cur.weight + adjEdge.weight;
                    pq.offer(new Edge(adjEdge.to, dist[adjEdge.to]));
                }
            }
        }
    }
}