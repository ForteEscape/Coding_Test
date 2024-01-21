import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[] color;
    static int V;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < N; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();

            for (int i = 0; i <= V; i++){
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            boolean flag = true;
            for (int i = 1; i <= N; i++){
                flag = bfs(i);

                if (!flag){
                    break;
                }
            }

            if (flag){
                System.out.println("possible");
            } else {
                System.out.println("impossible");
            }
        }
    }

    public static boolean bfs(int startNode){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(startNode);

        visited = new boolean[V + 1];
        color = new int[V + 1];

        visited[startNode] = true;
        color[startNode] = 1;

        while(!queue.isEmpty()){
            int cur = queue.pollFirst();

            for (int i = 0; i < graph.get(cur).size(); i++){
                int adjNode = graph.get(cur).get(i);

                if (visited[adjNode]){
                    if (color[adjNode] == color[cur]){
                        return false;
                    }

                    continue;
                }

                visited[adjNode] = true;
                if (color[cur] == 1){
                    color[adjNode] = 2;
                } else {
                    color[adjNode] = 1;
                }
                queue.addLast(adjNode);
            }
        }

        return true;
    }
}