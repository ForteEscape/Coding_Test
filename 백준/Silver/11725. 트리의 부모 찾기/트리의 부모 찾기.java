import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        boolean[] visited = new boolean[N + 1];
        int[] ans = new int[N + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()){
            int cur = queue.pollFirst();

            for (int element: graph.get(cur)){
                if (!visited[element]){
                    visited[element] = true;
                    ans[element] = cur;
                    queue.addLast(element);
                }
            }
        }

        for (int element: ans){
            if (element != 0){
                System.out.println(element);
            }
        }
    }
}