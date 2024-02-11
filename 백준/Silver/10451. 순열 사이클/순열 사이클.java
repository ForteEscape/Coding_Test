import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] size;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            size = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            int[] data = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int element = Integer.parseInt(st.nextToken());

                union(i, element);
            }

            Set<Integer> set = new HashSet<>();

            for (int i = 1; i <= N; i++) {
                set.add(find(i));
            }

            System.out.println(set.size());
        }
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            if (size[pa] >= size[pb]) {
                parent[pb] = pa;
                size[pa] += size[pb];
            } else {
                parent[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }
}