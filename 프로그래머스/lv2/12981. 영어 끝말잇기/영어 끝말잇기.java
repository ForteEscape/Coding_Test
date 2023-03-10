import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> wordSet = new HashSet<>();
        HashMap<Integer, Integer> turn = new HashMap<>();
        
        int personIdx = 0;
        int wordIdx = 0;
        
        while(true){    
            if(wordIdx >= words.length){
                answer[0] = 0;
                answer[1] = 0;
                break;
            }
            
            turn.put(personIdx, turn.getOrDefault(personIdx, 0) + 1);
            
            if(wordSet.contains(words[wordIdx])){ // 중복 발생
                System.out.println("err");
                answer[0] = personIdx + 1;
                answer[1] = turn.get(personIdx);
                break;
            } else{
                wordSet.add(words[wordIdx]);
                
                if(wordIdx != 0){
                    String beforeWord = words[wordIdx - 1];
                
                    if(beforeWord.charAt(beforeWord.length() - 1) != words[wordIdx].charAt(0)){
                        answer[0] = personIdx + 1;
                        answer[1] = turn.get(personIdx);
                        break;
                    }
                }
            }
            
            personIdx = (personIdx + 1) % n;
            wordIdx++;
        }

        return answer;
    }
}