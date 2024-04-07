import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] height;
    static int[] dp;
    static List<Integer>[] graph;
    static int V, E;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        height = new int[V + 1];
        graph = new List[V + 1];
        dp = new int[V + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(height[from] < height[to]) {
                graph[from].add(to);
            } else {
                graph[to].add(from);
            }
        }

        for(int i = 1; i <= V; i++) {
            int ans = dfs(i);
            System.out.println(ans);
        }
    }

    static int dfs(int cur) {
        if (dp[cur] != 0) {
            return dp[cur];
        }

        for (int element: graph[cur]) {
            dp[cur] = Math.max(dp[cur], dfs(element));
        }

        return ++dp[cur];
    }
}