import java.util.*;

class Solution {
    
    static ArrayList<Integer> ans;
    
    public int[] solution(long begin, long end) {
        int[] answer;
        
        ans = new ArrayList<>();
        for(long i = begin; i <= end; i++) {
            ans.add(solve(i));
        }
        
        answer = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
    
    static int solve(long x) {
        int res = 0;
        
        for(long i = 2; i <= Math.sqrt(x); i++) {
            if(x % i == 0) {
                res = (int)Math.max(res, i);
                
                if(x / i > 10000000) {
                    continue;
                }
                res = (int)Math.max(res, x / i);
            }
        }
        
        if(res == 0 && x != 1) {
            res = 1;
        }
        
        return res;
    }
}