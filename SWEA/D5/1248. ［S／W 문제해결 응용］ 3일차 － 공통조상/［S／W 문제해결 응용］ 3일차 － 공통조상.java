import java.io.*;
import java.util.*;

public class Solution {

    static int[][] parent;
    static int[] depth;
    static int[] size;
    static List<List<Integer>> tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            parent = new int[V + 1][15]; // 2^15 >= 10000
            depth = new int[V + 1];
            tree = new ArrayList<>();
            size = new int[V + 1];

            for(int i = 0; i <= V; i++){
                tree.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < E; i++){
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                tree.get(p).add(c);
                parent[c][0] = p;
            }

            initDepth(1);

            for (int i = 1; i <= V; i++){
                for (int j = 1; j < 15; j++){
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }

            if(depth[A] > depth[B]){
                int temp = A;
                A = B;
                B = temp;
            }

            // depth 맞추기
            int diff = (depth[B] - depth[A]);
            for (int i = 0; i < 15; i++) {
                if ((diff & (1 << i)) == (1 << i)) {
                    B = parent[B][i];
                }
            }

            int idx = 0;
            while (parent[A][0] != parent[B][0]) {
                int shift = Math.max(0, idx - 1);

                if (parent[A][shift] == 0) {
                    idx = 0;
                } else {
                    A = parent[A][shift];
                    B = parent[B][shift];
                    idx++;
                }
            }

            System.out.println("#" + test_case + " " + parent[A][0] + " " + (size[parent[A][0]] + 1));
        }
    }

    public static void initDepth(int idx){
        for (int i = 0; i < tree.get(idx).size(); i++){
            depth[tree.get(idx).get(i)] = depth[idx] + 1;
            initDepth(tree.get(idx).get(i));
            size[idx] += size[tree.get(idx).get(i)] + 1;
        }
    }
}