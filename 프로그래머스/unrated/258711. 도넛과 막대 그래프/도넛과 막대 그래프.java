import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        
        Map<Integer, int[]> map = new HashMap<>();
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            
            // indegree, outdegree
            if(!map.containsKey(from)){
                map.put(from, new int[]{0, 0});
            }
            
            if(!map.containsKey(to)){
                map.put(to, new int[]{0, 0});
            }
            
            map.get(from)[1]++;
            map.get(to)[0]++;
        }
        
        // [indegree, outdegree]
        for(int element: map.keySet()){
            // generateNode
            if(map.get(element)[0] == 0 && map.get(element)[1] >= 2){
                answer[0] = element;
            } else if(map.get(element)[0] > 0 && map.get(element)[1] == 0){ // bar graph
                answer[2]++;
            } else if(map.get(element)[0] >= 2 && map.get(element)[1] >= 2){ // eight shape graph
                answer[3]++;
            }
        }
        
        answer[1] = map.get(answer[0])[1] - (answer[2] + answer[3]);
        return answer;
    }
}