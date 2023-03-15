import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> ans = new ArrayList<>();
        Arrays.sort(arr);
        int idx = 0;
        
        for(int element: arr){
            if(element % divisor == 0){
                ans.add(element);
                idx++;
            }
        }
        
        int[] answer;
        
        if(idx == 0){
            answer = new int[]{-1};
        } else{
            answer = new int[idx];
            for(int i = 0; i < ans.size(); i++){
                answer[i] = ans.get(i);
            }
        }
        
        return answer;
    }
}