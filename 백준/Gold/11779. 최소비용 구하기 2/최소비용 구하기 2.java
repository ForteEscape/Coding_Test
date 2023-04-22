import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge{
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<ArrayList<Edge>> graph;
    static int[] dist;
    static int[] parents;
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        dist = new int[N + 1];
        parents = new int[N + 1];
        graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++){
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }

        // generate graph
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        Deque<Integer> stack = new ArrayDeque<>();
        System.out.println(dijkstra(startNode, endNode));

        int key = endNode;
        while(key != 0){
            stack.addLast(key);
            key = parents[key];
        }
        System.out.println(stack.size());

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pollLast()).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static int dijkstra(int startNode, int endNode){
        dist[startNode] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> (x.weight - y.weight));

        pq.offer(new Edge(startNode, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if(dist[cur.to] < cur.weight){
                continue;
            }

            for (int i = 0; i < graph.get(cur.to).size(); i++){
                Edge adjEdge = graph.get(cur.to).get(i);

                if (dist[adjEdge.to] > cur.weight + adjEdge.weight){
                    dist[adjEdge.to] = cur.weight + adjEdge.weight;

                    parents[adjEdge.to] = cur.to;
                    pq.offer(new Edge(adjEdge.to, dist[adjEdge.to]));
                }
            }
        }

        return dist[endNode];
    }
}