import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0){
            int digit = storey % 10;
            int next = (storey / 10) % 10;
            
            if(digit > 5){
                storey += 10;
                answer += 10 - digit;
            } else if(digit == 5){
                answer += 5;
                storey += next >= 5 ? 10 : 0;
            } else{
                answer += digit;
            }
            
            storey /= 10;
        }
        
        return answer;
    }
}