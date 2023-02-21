import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder sb = new StringBuilder();
        String answer = "";
        
        int[] counter = new int[10];
        int idx = 0;
        int x_len = X.length();
        int y_len = Y.length();
        
        int[] duplicated_num = new int[Math.max(x_len, y_len)];
        
        Arrays.fill(duplicated_num, -1);
        
        for(int i = 0; i < x_len; i++){
            counter[X.charAt(i) - '0']++;
        }
        
        for(int i = 0; i < y_len; i++){
            if(counter[Y.charAt(i) - '0'] > 0){
                counter[Y.charAt(i) - '0']--;
                duplicated_num[idx++] = Y.charAt(i) - '0';
            }
        }
        
        Arrays.sort(duplicated_num);
        
        for(int i = duplicated_num.length - 1; i >= 0; i--){
            if(duplicated_num[i] != -1){
                sb.append(duplicated_num[i]);
            }
        }
        
        answer = sb.toString();
        
        if(answer.equals("")){
            answer = "-1";
        }
        
        if(answer.charAt(0) == '0'){
            answer = "0";
        }
        
        return answer;
    }
}