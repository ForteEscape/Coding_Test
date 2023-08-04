import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>();
        char[][] data = {
            {'R', 'T'},
            {'C', 'F'},
            {'J', 'M'},
            {'A', 'N'}
        };
        
        for(int i = 0; i < 4; i++){
            map.put(data[i][0], 0);
            map.put(data[i][1], 0);
        }
        
        for(int i = 0; i < survey.length; i++){
            char compare1 = survey[i].charAt(0);
            char compare2 = survey[i].charAt(1);
            
            if(choices[i] <= 4){
                map.put(compare1, map.get(compare1) + (4 - choices[i]));
            } else{
                map.put(compare2, map.get(compare2) + (choices[i] - 4));
            }
        }
        
        String answer = "";
        for(int i = 0; i < 4; i++){
            if(map.get(data[i][0]) >= map.get(data[i][1])){
                answer += data[i][0];
            } else{
                answer += data[i][1];
            }
        }
        
        return answer;
    }
}