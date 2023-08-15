import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        Set<Character> set = new HashSet<>();
        
        for(int i = 0; i < skip.length(); i++){
            set.add(skip.charAt(i));
        }
        
        for(int i = 0; i < s.length(); i++){
            char tgt = s.charAt(i);
            int remainIdx = index;
            
            while(remainIdx > 0){
                tgt = (char)(tgt + 1);
                
                if(tgt > 'z'){
                    tgt = 'a';
                }
                
                if(!set.contains(tgt)){
                    remainIdx--;
                }
            }
            answer.append(tgt);
        }
        
        return answer.toString();
    }
}