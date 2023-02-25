import java.util.*;

class Solution {
    public int solution(int[] array) {
        int[] cnt = new int[1001];
        
        for(int element: array){
            cnt[element]++;
        }
        
        int max_cnt = Arrays.stream(cnt).max().getAsInt();
        int idx = -1;
        boolean found = false;
        
        for(int i = 0; i < cnt.length; i++){
            if(max_cnt == cnt[i]){
                if(found){
                    return -1;
                }
                found = true;
                idx = i;
            }
        }
        
        return idx;
        
    }
}