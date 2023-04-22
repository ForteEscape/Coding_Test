import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class City{
        int to;
        int weight;

        City(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static int N;
    static ArrayList<ArrayList<City>> graph;
    static ArrayList<ArrayList<City>> inverseGraph;
    static int cnt;
    static int[] dist;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        inverseGraph = new ArrayList<>();
        parent = new int[V + 1];
        visited = new boolean[V + 1];
        dist = new int[V + 1];

        for (int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
            inverseGraph.add(new ArrayList<>());
        }


        //graph construction
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new City(to, weight));
            parent[to]++;
            inverseGraph.get(to).add(new City(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        solve(startNode, endNode);

        System.out.println(dist[endNode]);
        System.out.println(cnt);
    }

    static void solve(int start, int end){
        Deque<Integer> queue = new ArrayDeque<>();

        queue.addLast(start);
        while(!queue.isEmpty()){
            int cur = queue.pollFirst();

            for (int i = 0; i < graph.get(cur).size(); i++){
                City adjCity = graph.get(cur).get(i);
                parent[adjCity.to]--;

                if (!visited[adjCity.to] && parent[adjCity.to] == 0){
                    visited[adjCity.to] = true;
                    queue.addLast(adjCity.to);
                }

                dist[adjCity.to] = Math.max(dist[adjCity.to], dist[cur] + adjCity.weight);
            }
        }

        queue = new ArrayDeque<>();
        cnt = 0;

        queue.addLast(end);
        while(!queue.isEmpty()){
            int cur = queue.pollFirst();

            for (int i = 0; i < inverseGraph.get(cur).size(); i++){
                City adjCity = inverseGraph.get(cur).get(i);

                if (adjCity.weight != 0 && adjCity.weight == dist[cur] - dist[adjCity.to]){
                    queue.addLast(adjCity.to);
                    cnt++;
                    adjCity.weight = 0;
                }
            }
        }

    }
}