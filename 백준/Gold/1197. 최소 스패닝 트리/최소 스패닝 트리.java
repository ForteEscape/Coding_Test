import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] graph = new int[E][3];

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[i] = new int[]{from, to, weight};
        }

        parents = new int[N + 1];
        size = new int[N + 1];
        for (int i = 1; i <= N; i++){
            parents[i] = i;
            size[i] = 1;
        }

        System.out.println(solve(graph, N, E));
    }

    public static int solve(int[][] graph, int v, int e){
        Arrays.sort(graph, (x, y) -> (x[2] - y[2]));
        int weightSum = 0;
        int cnt = 0;

        for(int i = 0; i < e; i++){
            if (cnt == v - 1){
                return weightSum;
            }

            if (find(graph[i][0]) != find(graph[i][1])){
                weightSum += graph[i][2];
                union(graph[i][0], graph[i][1]);
                cnt++;
            }
        }

        return weightSum;
    }

    public static void union(int u, int v){
        int pu = find(u);
        int pv = find(v);

        if (size[pu] > size[pv]){
            parents[pv] = pu;
            size[pu] += size[pv];
        } else{
            parents[pu] = pv;
            size[pv] += size[pu];
        }
    }

    public static int find(int x){
        if (x == parents[x]){
            return x;
        }

        return parents[x] = find(parents[x]);
    }
}