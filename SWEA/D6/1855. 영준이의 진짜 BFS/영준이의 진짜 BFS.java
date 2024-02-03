import java.io.*;
import java.util.*;

public class Solution {

    static List<List<Integer>> graph;
    static int[] travelList;
    static int[][] parent;
    static int[] depth;
    static StringTokenizer st;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            travelList = new int[N];
            parent = new int[N + 1][18]; // 2^17 > 100000
            graph = new ArrayList<>();
            depth = new int[N + 1];

            for (int i = 0; i <= N; i++){
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 2; i <= N; i++){
                int p = Integer.parseInt(st.nextToken());
                parent[i][0] = p;
                graph.get(p).add(i);
            }

            bfs(1);

            // init parent
            for (int i = 1; i <= N; i++){
                for (int j = 1; j < parent[0].length; j++){
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }

            //System.out.println(Arrays.toString(depth));
            //System.out.println(Arrays.toString(travelList));
            long ans = 0;
            for (int i = 0; i < N - 1; i++){
                int common = LCA(travelList[i], travelList[i + 1]);
                ans += (depth[travelList[i]] + depth[travelList[i + 1]]) - (2L * depth[common]);
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

    // LCA query
    public static int LCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        //System.out.println(a + " " + b);
        int diff = depth[b] - depth[a];
        for (int i = 0; i < parent[0].length; i++) {
            if ((diff & (1 << i)) == (1 << i)) {
                b = parent[b][i];
            }
        }

        if (a == b) {
            return a;
        }

        //System.out.println(a + " " + b);
        int idx = 0;
        for (int i = parent[0].length - 1; i >= 0; i--){
            if (parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    public static void bfs(int startNode) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(startNode);

        travelList[0] = startNode;
        int idx = 1;

        while(!queue.isEmpty()) {
            int cur = queue.pollFirst();

            for (int i = 0; i < graph.get(cur).size(); i++){
                int adjNode = graph.get(cur).get(i);

                queue.addLast(adjNode);
                travelList[idx++] = adjNode;
                depth[adjNode] = depth[cur] + 1;
            }
        }
    }
}