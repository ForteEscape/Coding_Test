import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> toppingCount = new HashMap<>();
        Set<Integer> divideToppingSet = new HashSet<>();
        
        for(int i = 0; i < topping.length; i++){
            toppingCount.put(topping[i], toppingCount.getOrDefault(topping[i], 0) + 1);
        }
        
        for(int i = 0; i < topping.length; i++){
            toppingCount.put(topping[i], toppingCount.get(topping[i]) - 1);
            divideToppingSet.add(topping[i]);
            
            if(toppingCount.get(topping[i]) == 0){
                toppingCount.remove(topping[i]);
            }
            
            if(toppingCount.size() == divideToppingSet.size()){
                answer++;
            }
        }
        
        return answer;
    }
}