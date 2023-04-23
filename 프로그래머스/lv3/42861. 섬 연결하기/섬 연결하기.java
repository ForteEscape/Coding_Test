import java.util.*;

class Solution {
    static int[] parents;
    static int[] size;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n + 1];
        size = new int[n + 1];
        
        for(int i = 1; i < n + 1; i++){
            parents[i] = i;
            size[i] = 1;
        }
        
        return kruskal(costs, n);
    }
    
    static int kruskal(int[][] graph, int N){
        int cnt = 0;
        int weightSum = 0;
        
        Arrays.sort(graph, (x, y) -> (x[2] - y[2]));
        
        for(int i = 0; i < graph.length; i++){
            if(cnt == N - 1){
                return weightSum;
            }
            
            int u = graph[i][0];
            int v = graph[i][1];
            
            if(find(u) != find(v)){
                weightSum += graph[i][2];
                union(u, v);
                cnt++;
            }
        }
        
        return weightSum;
    }
    
    static void union(int u, int v){
        int pu = find(u);
        int pv = find(v);
        
        if(size[pu] > size[pv]){
            parents[pv] = pu;
            size[pu] += size[pv];
        } else{
            parents[pu] = pv;
            size[pv] += size[pu];
        }
    }
    
    static int find(int v){
        if(v == parents[v]){
            return v;
        }
        
        return parents[v] = find(parents[v]);
    }
}