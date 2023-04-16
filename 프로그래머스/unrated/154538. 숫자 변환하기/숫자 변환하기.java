import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] dx = {n, 2, 3};
        int[] visited;
        
        Deque<Integer> queue = new ArrayDeque<>();
        visited = new int[y + 1];
        
        queue.addLast(x);
        visited[x] = 1;
        
        while(!queue.isEmpty()){
            int cur = queue.pollFirst();
            
            if(cur == y){
                return visited[cur] - 1;
            }
            
            for(int i = 0; i < 3; i++){
                int next;
                if(i == 0){
                    next = cur + dx[i];
                } else{
                    next = cur * dx[i];
                }
                
                if(next > y){
                    continue;
                }
                
                if(visited[next] == 0){
                    visited[next] = visited[cur] + 1;
                    queue.addLast(next);
                }
            }
        }
        
        return -1;
    }
}