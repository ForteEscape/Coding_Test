import java.util.*;

class Solution {
    public String solution(String new_id) {
        Deque<Character> deque = new ArrayDeque<>();
        
        StringBuilder lowerCase = new StringBuilder();
        for(int i = 0; i < new_id.length(); i++){
            if(new_id.charAt(i) >= 'A' && new_id.charAt(i) <= 'Z'){
                lowerCase.append((char)(new_id.charAt(i) + 32));
            } else{
                lowerCase.append(new_id.charAt(i));
            }
        }
        
        new_id = lowerCase.toString();
        
        for(int i = 0; i < new_id.length(); i++){
            char c = new_id.charAt(i);
            
            if(c == '.'){
                if(deque.isEmpty() || deque.peekLast() != '.'){
                    deque.addLast(c);
                    continue;
                } else{
                    continue;
                }
            }
            
            if(c == '-' || c == '_'){
                deque.addLast(c);
                continue;
            }
            
            if(c >= 'a' && c <= 'z'){
                deque.addLast(c);
                continue;
            }
            
            if(c >= '0' && c <= '9'){
                deque.addLast(c);
                continue;
            }
        }
        
        if(!deque.isEmpty()){
            if(deque.peekFirst() == '.'){
                deque.pollFirst();
            }
            
            if(!deque.isEmpty() && deque.peekLast() == '.'){
                deque.pollLast();
            }
        } 
        
        if(deque.isEmpty()){
            deque.addLast('a');
        }
        
        if(deque.size() >= 16){
            while(deque.size() >= 16){
                deque.pollLast();
            }
            
            if(deque.peekLast() == '.'){
                deque.pollLast();
            }
        }
        
        while(deque.size() <= 2){
            deque.addLast(deque.peekLast());
        }
        
        StringBuilder sb = new StringBuilder();
        for(char element: deque){
            sb.append(element);
        }
        
        return sb.toString();
    }
}