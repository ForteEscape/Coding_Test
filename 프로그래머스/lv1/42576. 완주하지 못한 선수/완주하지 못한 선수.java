import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < completion.length; i++){
            map.put(completion[i], map.getOrDefault(completion[i], 0) + 1);
        }
        
        for(String element : participant){
            if(!map.containsKey(element)){
                return element;
            }
            
            map.put(element, map.get(element) - 1);
            if(map.get(element) == 0){
                map.remove(element);
            }
        }
        
        for(String key : map.keySet()){
            return key;
        }
        
        return answer;
    }
}