import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        Set<Integer> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        
        for(int element: delete_list){
            set.add(element);
        }
        
        for(int element: arr){
            if(!set.contains(element)){
                ans.add(element);
            }
        }
        
        int[] answer = new int[ans.size()];
        int idx = 0;
        for(int element: ans){
            answer[idx++] = element;
        }
        return answer;
    }
}