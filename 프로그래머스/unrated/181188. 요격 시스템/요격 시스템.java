import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (x, y) -> (x[1] - y[1]));
        int end = targets[0][1];
        
        for(int[] target: targets){
            int targetStart = target[0];
            int targetEnd = target[1];
            
            if(end <= targetStart){
                answer++;
                end = targetEnd;
            }
        }
        
        return answer + 1;
    }
}