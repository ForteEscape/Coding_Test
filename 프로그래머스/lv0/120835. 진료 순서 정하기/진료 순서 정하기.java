import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        int length = emergency.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int[] answer = new int[length];
        int[] tmp = new int[length];
        
        for(int i = 0; i < length; i++){
            tmp[i] = emergency[i];
        }
        
        Arrays.sort(tmp);
        
        for(int i = length - 1, idx = 1; i >= 0; i--){
            map.put(tmp[i], idx++);
        }
        
        for(int i = 0; i < length; i++){
            answer[i] = map.get(emergency[i]);
        }
        
        return answer;
    }
}