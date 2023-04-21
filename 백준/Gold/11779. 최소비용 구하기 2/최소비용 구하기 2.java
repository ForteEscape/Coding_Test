import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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

    static int[] dist;
    final static int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Edge>> graph;
    static int V, E;
    static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Edge(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        tmp = new int[V + 1];

        System.out.println(dijkstra(startNode, endNode));
        Deque<Integer> stack = new ArrayDeque<>();

        int keyIdx = endNode;
        while(keyIdx != 0){
            stack.addLast(keyIdx);
            keyIdx = tmp[keyIdx];
        }

        System.out.println(stack.size());
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pollLast()).append(" ");
        }
        System.out.println(sb.toString());

    }

    static int dijkstra(int startNode, int endNode){
        dist = new int[V + 1];
        for (int i = 0; i < V + 1; i++){
            dist[i] = INF;
        }
        dist[startNode] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> (x.weight - y.weight));
        pq.offer(new Edge(startNode, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if (dist[cur.to] < cur.weight){
                continue;
            }

            for(int i = 0; i < graph.get(cur.to).size(); i++){
                Edge edge = graph.get(cur.to).get(i);

                if (dist[edge.to] > edge.weight + cur.weight){
                    tmp[edge.to] = cur.to;
                    dist[edge.to] = edge.weight + cur.weight;
                    pq.offer(new Edge(edge.to, dist[edge.to]));
                }
            }
        }

        return dist[endNode];
    }


}