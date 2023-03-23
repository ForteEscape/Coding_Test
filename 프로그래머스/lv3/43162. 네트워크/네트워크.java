/*
  0 1 2
0 1 1 0
1 1 1 1
2 0 1 1

*/

import java.util.*;

class Solution {
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                
                if(computers[i][j] == 1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                BFS(graph, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void BFS(ArrayList<ArrayList<Integer>> graph, int start){
        Deque<Integer> queue = new ArrayDeque<>();
        
        visited[start] = true;
        queue.addLast(start);
        
        while(!queue.isEmpty()){
            Integer cur = queue.pollFirst();
            
            for(Integer element: graph.get(cur)){
                if(!visited[element]){
                    visited[element] = true;
                    queue.addLast(element);
                }
            }
        }
    }
}