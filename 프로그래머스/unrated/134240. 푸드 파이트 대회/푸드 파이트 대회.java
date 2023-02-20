import java.util.*;

class Solution {
    public String solution(int[] food) {
        String answer = "";
        String front_str = "";
        String back_str = "";
        Deque<String> front = new ArrayDeque<>();
        Deque<String> back = new ArrayDeque<>();
        
        for(int i = 1; i < food.length; i++){
            int cnt = food[i] / 2;
            
            for(int j = 0; j < cnt; j++){
                String element = Integer.toString(i);
                front.addFirst(element);
                back.addFirst(element);
            }
        }
        
        while(!front.isEmpty()){
            front_str += front.pollLast();
            back_str += back.pollFirst();
        }
        
        answer = front_str + "0" + back_str;
        
        return answer;
    }
}