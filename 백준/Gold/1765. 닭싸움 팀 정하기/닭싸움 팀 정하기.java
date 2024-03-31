import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] size;
    static List<Integer>[] friendGraph;
    static List<Integer>[] enemyGraph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        size = new int[N + 1];
        friendGraph = new List[N + 1];
        enemyGraph = new List[N + 1];

        for(int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
            friendGraph[i] = new ArrayList<>();
            enemyGraph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            char opt = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (opt == 'E') {
                enemyGraph[a].add(b);
                enemyGraph[b].add(a);
            } else {
                friendGraph[a].add(b);
                friendGraph[b].add(b);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < enemyGraph[i].size(); j++) {
                int n = enemyGraph[i].get(j);

                for (int k = 0; k < enemyGraph[n].size(); k++) {
                    if (i == enemyGraph[n].get(k)) continue;

                    friendGraph[i].add(enemyGraph[n].get(k));
                    friendGraph[enemyGraph[n].get(k)].add(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < friendGraph[i].size(); j++) {
                union(i, friendGraph[i].get(j));
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= N; i++) {
            set.add(find(i));
        }
        
        System.out.println(set.size());
    }

    static void union(int a, int b) {
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

    static int find(int x) {
        if(x == parent[x]) {
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }
}