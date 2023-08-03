import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        List<Integer> stack = new ArrayList<>();
        
        for(int element: ingredient){
            stack.add(element);
            
            if(stack.size() >= 4 && isHamberger(stack)){
                answer++;
                for(int i = 0; i < 4; i++){
                    stack.remove(stack.size() - 1);
                }
            }
        }
        return answer;
    }
    
    public boolean isHamberger(List<Integer> stack){
        return stack.get(stack.size() - 1) == 1 && stack.get(stack.size() - 2) == 3 
            && stack.get(stack.size() - 3) == 2 && stack.get(stack.size() - 4) == 1;
    }
}