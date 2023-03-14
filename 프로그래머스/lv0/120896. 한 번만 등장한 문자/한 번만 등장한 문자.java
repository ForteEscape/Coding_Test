import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        char key = 'a';
        for(int i = 0; i < 26; i++){
            if(map.getOrDefault((char)(key + i), 0) == 1){
                sb.append((char)(key + (char)i));
            }
        }
        
        return sb.toString();
    }
}