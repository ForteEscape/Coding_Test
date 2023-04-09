import java.util.*;

class Solution {
    public int[] solution(String s) {
        HashMap<Character, Integer> charDist = new HashMap<>();
        int[] answer = new int[s.length()];
        
        for(int i = 0; i < s.length(); i++){
            if(charDist.containsKey(s.charAt(i))){
                answer[i] = i - charDist.get(s.charAt(i));
            } else{
                answer[i] = -1;
            }
            charDist.put(s.charAt(i), i);
        }
        
        return answer;
    }
}