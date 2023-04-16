import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++){
            parents[i] = i;
            size[i] = 1;
        }

        int answer = 0;

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (find(u) != find(v)){
                union(u, v);
            } else{
                answer = i;
                break;
            }
        }

        System.out.println(answer);
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

    public static int find(int x){ // union - find with path compression
        if (x == parents[x]){
            return x;
        }
        return parents[x] = find(parents[x]);
    }
}