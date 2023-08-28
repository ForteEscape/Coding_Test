import java.util.*;

class Solution {
    
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int nodeCnt = 1;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        for(int i = 0; i < wires.length; i++){
            graph = new ArrayList<>();
            visited = new boolean[n + 1];
            int[] towerCnt = new int[2];
            int idx = 0;
            
            for(int j = 0; j <= n; j++){
                graph.add(new ArrayList<>());
            }
            
            for(int j = 0; j < wires.length; j++){
                if(i == j) continue;
                
                graph.get(wires[j][0]).add(wires[j][1]);
                graph.get(wires[j][1]).add(wires[j][0]);
            }
            
            for(int j = 1; j <= n; j++){
                if(!visited[j]){
                    visited[j] = true;
                    nodeCnt = 1;
                    dfs(j);
                    
                    towerCnt[idx++] = nodeCnt;
                }
            }
            
            answer = Math.min(answer, Math.abs(towerCnt[0] - towerCnt[1]));
        }
        
        return answer;
    }
    
    public static void dfs(int startNode){
        for(int i = 0; i < graph.get(startNode).size(); i++){
            int adjNode = graph.get(startNode).get(i);
            
            if(!visited[adjNode]){
                nodeCnt++;
                visited[adjNode] = true;
                dfs(adjNode);
            }
        }
    }
}