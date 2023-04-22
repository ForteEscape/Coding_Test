import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;
    static int[] score;
    static int[] seq;
    static long[] sum;
    static int[] size;
    static long ansSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        score = new int[N + 1];
        seq = new int[N + 1];
        sum = new long[N + 1];
        size = new int[N + 1];
        ansSum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        long ans = 0;

        for (int i = N; i > 1; i--){
            int k = seq[i];

            parents[k] = k;
            sum[k] = score[k];
            size[k] = 1;

            ansSum += sum[k];

            if (k - 1 >= 0 && parents[k - 1] != 0){
                union(k - 1, k);
            }

            if (k + 1 <= N && parents[k + 1] != 0){
                union(k, k + 1);
            }
            
            ans = Math.max(ans, ansSum);
        }
        System.out.println(ans);
    }

    public static void union(int u, int v){
        int pu = find(u);
        int pv = find(v);
        
        ansSum -= sum[pu] * size[pu];
        ansSum -= sum[pv] * size[pv];

        if(size[pu] > size[pv]){
            parents[pv] = pu;
            size[pu] += size[pv];
            sum[pu] += sum[pv];

            ansSum += sum[pu] * size[pu];
        } else{
            parents[pu] = pv;
            size[pv] += size[pu];
            sum[pv] += sum[pu];

            ansSum += sum[pv] * size[pv];
        }
    }

    public static int find(int v){
        if (v == parents[v]){
            return v;
        }

        return parents[v] = find(parents[v]);
    }
}