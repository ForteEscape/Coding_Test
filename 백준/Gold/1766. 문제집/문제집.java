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

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            inDegree[to]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++){
            if (inDegree[i] == 0){
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for (int i = 0; i < graph.get(cur).size(); i++){
                int adjNode = graph.get(cur).get(i);
                inDegree[adjNode]--;

                if (inDegree[adjNode] == 0){
                    pq.offer(adjNode);
                }
            }
        }

        System.out.println(sb);
    }
}
