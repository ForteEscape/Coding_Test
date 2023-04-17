import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = numbers.length - 1; i >= 0; i--){
            int num = numbers[i];
            
            while(!stack.isEmpty() && stack.peekLast() <= num){
                stack.pollLast();
            }
            
            if(stack.isEmpty()){
                answer[i] = -1;
            } else{
                answer[i] = stack.peekLast();
            }
            
            stack.addLast(num);
        }
        
        return answer;
    }
}