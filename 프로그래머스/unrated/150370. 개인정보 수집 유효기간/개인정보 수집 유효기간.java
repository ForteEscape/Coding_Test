import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        HashMap<String, Integer> term = new HashMap<>();
        int[] todayData = new int[3];
        
        // today
        StringTokenizer st = new StringTokenizer(today, ".");
        for(int i = 0; i < 3; i++){
            todayData[i] = Integer.parseInt(st.nextToken());
        }
        
        
        //terms
        for(int i = 0; i < terms.length; i++){
            st = new StringTokenizer(terms[i]);
            term.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < privacies.length; i++){
            st = new StringTokenizer(privacies[i]);
            
            String date = st.nextToken();
            String type = st.nextToken();
            
            int[] getDate = new int[3];
            st = new StringTokenizer(date, ".");
            
            for(int j = 0; j < 3; j++){
                getDate[j] = Integer.parseInt(st.nextToken());
            }
            
            getDate[1] += term.get(type);
            
            if(getDate[1] > 12){
                while(getDate[1] > 12){
                    getDate[0]++;
                    getDate[1] -= 12;
                }
            }
            
            getDate[2]--;
            
            if(getDate[2] == 0){
                getDate[2] += 28;
                getDate[1]--;
                
                if(getDate[1] == 0){
                    getDate[1] = 12;
                    getDate[0]--;
                }
            }
            
            if(todayData[0] > getDate[0]){
                result.add(i + 1);
            } else if(todayData[0] == getDate[0]){
                if(todayData[1] > getDate[1]){
                    result.add(i + 1);
                } else if(todayData[1] == getDate[1]){
                    if(todayData[2] > getDate[2]){
                        result.add(i + 1);
                    }
                }
            }
        }
        
        int[] answer = new int[result.size()];
        int idx = 0;
        
        for(int element: result){
            answer[idx++] = element;
        }
        
        return answer;
    }
}