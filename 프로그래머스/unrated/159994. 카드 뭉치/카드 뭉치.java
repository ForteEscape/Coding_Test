import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        
        Deque<String> card1 = new ArrayDeque<>();
        Deque<String> card2 = new ArrayDeque<>();
        
        for(int i = 0; i < cards1.length; i++){
            card1.addLast(cards1[i]);
        }
        
        for(int i = 0; i < cards2.length; i++){
            card2.addLast(cards2[i]);
        }
        
        int idx = 0;
        while(idx < goal.length){
            // 1번 카드가 비어 있는 경우
            if(card1.isEmpty()){
                if(card2.peekFirst().equals(goal[idx])){
                    card2.pollFirst();
                } else{
                    return "No";
                }
            } else if(card2.isEmpty()){
                if(card1.peekFirst().equals(goal[idx])){
                    card1.pollFirst();
                } else{
                    return "No";
                }
            } else{
                if(card1.peekFirst().equals(goal[idx])){
                    card1.pollFirst();
                } else if(card2.peekFirst().equals(goal[idx])){
                    card2.pollFirst();
                } else{
                    return "No";
                }
            }
            
            idx++;
        }
        
        return "Yes";
    }
}