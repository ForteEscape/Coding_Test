import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer;
        int ansLength = 0;
        HashMap<String, String> userData = new HashMap<>();
        
        for(String operation: record){
            StringTokenizer st = new StringTokenizer(operation);
            
            String operator = st.nextToken();
            String uid = st.nextToken();
            
            if(operator.equals("Enter") || operator.equals("Change")){
                String userName = st.nextToken();
                userData.put(uid, userName);
                
                if(operator.equals("Enter")) ansLength++;
            } 
            
            if(operator.equals("Leave")){
                ansLength++;
            }
        }
        
        answer = new String[ansLength];
        int ansIdx = 0;
        
        for(String operation: record){
            StringTokenizer st = new StringTokenizer(operation);
            
            String operator = st.nextToken();
            String uid = st.nextToken();
            
            if(operator.equals("Enter")){
                answer[ansIdx++] = userData.get(uid) + "님이 들어왔습니다.";
            } else if(operator.equals("Leave")){
                answer[ansIdx++] = userData.get(uid) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}