import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Deque<Character> deque = new ArrayDeque<>();
        
        for(int i = 0; i < number.length(); i++){
            while(!deque.isEmpty() && deque.peekLast() < number.charAt(i) && k > 0){
                deque.pollLast();
                k--;
            }
            
            deque.addLast(number.charAt(i));
        }
        
        while(k > 0){
            deque.pollLast();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        for(Character element: deque){
            sb.append(element);
        }
        
        return sb.toString();
    }
}