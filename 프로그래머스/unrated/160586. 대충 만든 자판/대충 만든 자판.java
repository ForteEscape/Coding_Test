import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        
        for(String element: keymap){
            for(int i = 0; i < element.length(); i++){
                map.put(element.charAt(i), Math.min(i + 1, map.getOrDefault(element.charAt(i), Integer.MAX_VALUE)));
            }
        }
        
        for(String target: targets){
            int sum = 0;
            for(int i = 0; i < target.length(); i++){
                if(!map.containsKey(target.charAt(i))){
                    sum = -1;
                    break;
                }
                
                sum += map.get(target.charAt(i));
            }
            
            ans.add(sum);
        }
        
        int[] answer = new int[ans.size()];
        
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}