import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++){
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[N + 1];

        // graph construction
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            inDegree[to]++;
            graph.get(from).add(to);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++){
            if (inDegree[i] == 0){
                queue.addLast(i);
            }
        }

        int[] answer = new int[N];
        int idx = 0;

        while(!queue.isEmpty()){
            int cur = queue.pollFirst();

            answer[idx++] = cur;

            for (int i = 0; i < graph.get(cur).size(); i++){
                int adj = graph.get(cur).get(i);

                inDegree[adj]--;
                if (inDegree[adj] == 0){
                    queue.addLast(adj);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int element: answer){
            sb.append(element).append(" ");
        }

        System.out.println(sb.toString());
    }
}