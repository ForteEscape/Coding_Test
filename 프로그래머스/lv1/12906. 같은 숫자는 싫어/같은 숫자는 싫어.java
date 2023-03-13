import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int i = 0; i < arr.length; i++){
            if(deque.isEmpty()){
                deque.addLast(arr[i]);
            } else{
                if(deque.peekLast() != arr[i]){
                    deque.addLast(arr[i]);
                }
            }
        }
        
        answer = new int[deque.size()];
        int idx = 0;
        
        while(!deque.isEmpty()){
            answer[idx++] = deque.pollFirst();
        }

        return answer;
    }
}