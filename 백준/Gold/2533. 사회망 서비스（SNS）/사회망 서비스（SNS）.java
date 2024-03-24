import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp;
    static List<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];
        tree = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree[from].add(to);
            tree[to].add(from);
        }

        visited = new boolean[N + 1];
        solve(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void solve(int start) {
        visited[start] = true;
        dp[start][0] = 1;

        for (int i = 0; i < tree[start].size(); i++) {
            int adjNode = tree[start].get(i);

            if (visited[adjNode]) {
                continue;
            }

            solve(adjNode);
            dp[start][1] += dp[adjNode][0];
            dp[start][0] += Math.min(dp[adjNode][0], dp[adjNode][1]);
        }
    }
}