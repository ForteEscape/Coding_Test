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

    static int[] dist;
    final static int INF = 987654321;
    static Edge[] edges;
    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int j = 0; j <= N; j++){
                graph.add(new ArrayList<>());
            }

            for(int j = 0; j < M; j++){
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Edge(to, weight));
                graph.get(to).add(new Edge(from, weight));
            }

            for(int j = 0; j < W; j++){
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Edge(to, -weight));
            }

            if (bellman_ford(N)){
                System.out.println("YES");
            } else{
                System.out.println("NO");
            }
        }
    }

    static boolean bellman_ford(int N){
        dist = new int[N + 1];

        for(int i = 0; i <= N; i++){
            dist[i] = INF;
        }
        dist[1] = 0;

        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++){
                for (Edge edge: graph.get(j)){
                    if (dist[edge.to] > dist[j] + edge.weight){
                        dist[edge.to] = dist[j] + edge.weight;

                        if(i == N){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}