import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] size;
    static Map<String, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        map = new HashMap<>();

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            map.clear();
            parent = new int[200_002];
            size = new int[200_002];

            for (int i = 0; i < 200_002; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            int idx = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                String from = st.nextToken();
                String to = st.nextToken();

                if (!map.containsKey(from)) {
                    map.put(from, idx);
                    idx++;
                }

                if (!map.containsKey(to)) {
                    map.put(to, idx);
                    idx++;
                }

                if(find(map.get(from)) != find(map.get(to))) {
                    union(map.get(from), map.get(to));
                }
                System.out.println(size[find(map.get(from))]);
            }
        }
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(size[pa] >= size[pb]) {
            parent[pb] = pa;
            size[pa] += size[pb];
        } else {
            parent[pa] = pb;
            size[pb] += size[pa];
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }
}