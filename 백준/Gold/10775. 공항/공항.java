import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;
    public static int[] size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        size = new int[G + 1];

        for (int i = 1; i <= G; i++){
            parent[i] = i;
            size[i] = 1;
        }

        int ans = 0;
        for (int i = 0; i < P; i++){
            int gate = Integer.parseInt(br.readLine());
            int emptyGate = find(gate);

            if(emptyGate == 0){
                break;
            }

            union(emptyGate, emptyGate - 1);
            ans++;
        }

        System.out.println(ans);
    }

    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pa] = pb;
        }
    }

    public static int find(int x){
        if (x == parent[x]){
            return parent[x];
        }

        return parent[x] = find(parent[x]);
    }
}
