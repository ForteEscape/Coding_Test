import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<String, Integer> dict = new HashMap<>();
        boolean[] visited = new boolean[msg.length()];
        
        for(int i = 0; i < 26; i++){
            char character = (char)('A' + (char)i);
            dict.put(Character.toString(character), i + 1);
        }
        
        int idx = 27;
        
        for(int i = 0; i < msg.length(); i++){
            if(visited[i]){
                continue;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(i));
            int searchIdx = 0;
            
            if(dict.containsKey(sb.toString())){
                visited[i] = true;
                searchIdx = dict.get(sb.toString());
            }
            
            for(int j = i + 1; j < msg.length(); j++){
                sb.append(msg.charAt(j));
                
                if(dict.containsKey(sb.toString())){
                    visited[j] = true;
                    searchIdx = dict.get(sb.toString());
                } else{
                    dict.put(sb.toString(), idx++);
                    break;
                }
            }
            ans.add(searchIdx);
        }
        
        int[] answer = new int[ans.size()];
        
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}