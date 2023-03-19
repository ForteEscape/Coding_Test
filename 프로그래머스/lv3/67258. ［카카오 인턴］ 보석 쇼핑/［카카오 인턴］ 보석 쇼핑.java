import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>();
        
        for(int i = 0; i < gems.length; i++){
            set.add(gems[i]);
        }
        
        int start = 0;
        int end = 0;
        
        HashMap<String, Integer> boughtGem = new HashMap<>();
        int length = Integer.MAX_VALUE;
        
        while(true){           
            if(boughtGem.size() >= set.size()){
                int remainGem = boughtGem.get(gems[start]);
                
                if(remainGem == 1){
                    boughtGem.remove(gems[start]);
                } else{
                    boughtGem.put(gems[start], remainGem - 1);
                }
                
                start++;
            } else if(end >= gems.length){
                break;
            } else{
                boughtGem.put(gems[end], boughtGem.getOrDefault(gems[end], 0) + 1);
                end++;
            }
            
            if(boughtGem.size() == set.size()){
                if(end - start < length){
                    length = end - start;
                    answer[0] = start + 1;
                    answer[1] = end;
                }
            }
            
        }
        
        return answer;
    }
}