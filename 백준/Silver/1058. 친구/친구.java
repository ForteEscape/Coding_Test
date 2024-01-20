import java.util.*;
import java.io.*;

public class Main {

    static List<List<Integer>> graph;
    static int N;
    static int ans;

    static class Node {
        int element;
        int cnt;

        Node(int element, int cnt){
            this.element = element;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++){
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++){
                if (line.charAt(j) == 'Y'){
                    graph.get(i).add(j + 1);
                }
            }
        }

        ans = 0;

        for (int i = 1; i <= N; i++){
            bfs(i);
        }

        System.out.println(ans);
    }

    public static void bfs(int startNode){
        boolean[] visited = new boolean[N + 1];
        Deque<Node> queue = new ArrayDeque<>();

        queue.addLast(new Node(startNode, 0));
        visited[startNode] = true;

        int cnt = 0;

        while(!queue.isEmpty()){
            Node cur = queue.pollFirst();

            if (cur.cnt >= 2){
                continue;
            }

            for (int i = 0; i < graph.get(cur.element).size(); i++){
                int adjNode = graph.get(cur.element).get(i);

                if (!visited[adjNode]){
                    visited[adjNode] = true;
                    Node nextNode = new Node(adjNode, cur.cnt + 1);
                    queue.addLast(nextNode);

                    cnt++;
                }
            }
        }

        ans = Math.max(ans, cnt);
    }
}