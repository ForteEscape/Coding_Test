import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        int currentMoney = 0;
        
        Arrays.sort(d);
        
        for(int i = 0; i < d.length; i++){
            if(currentMoney + d[i] <= budget){
                currentMoney += d[i];
                answer++;
            }
        }
        
        return answer;
    }
}