import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j = 0; j < K - 1; j++){
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                inDegree[to]++;

                from = to;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++){
            if (inDegree[i] == 0){
                queue.addLast(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            int cur = queue.pollFirst();

            ans.add(cur);
            for (int i = 0; i < graph.get(cur).size(); i++){
                int adjNode = graph.get(cur).get(i);

                inDegree[adjNode]--;

                if (inDegree[adjNode] == 0){
                    queue.addLast(adjNode);
                }
            }
        }

        if (ans.size() != N){
            System.out.println(0);
        } else {
            for (int element: ans){
                System.out.println(element);
            }
        }
    }
}
