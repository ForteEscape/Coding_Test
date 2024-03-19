import java.util.*;

class Solution {
    static Map<String, Boolean> wordMap;
    static int ans = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        wordMap = new HashMap<>();
        for(String word : words) {
            wordMap.put(word, false);
        }
        
        if(!wordMap.containsKey(target)) {
            return 0;
        }
        
        solve(0, begin.toCharArray(), target);
        
        return ans;
    }
    
    static void solve(int cnt, char[] data, String target) {
        if(target.equals(String.valueOf(data))) {
            ans = Math.min(ans, cnt);
            return;
        }
        
        for(int i = 0; i < data.length; i++) {
            char tmp = data[i];
            for(int j = 1; j < 26; j++) {
                int next = ((data[i] - 'a') + j) % 26;
                
                data[i] = (char)(next + 'a');
                
                if(wordMap.containsKey(String.valueOf(data)) && !wordMap.get(String.valueOf(data))) {
                    //System.out.println(String.valueOf(data) + " " + cnt);
                    
                    wordMap.put(String.valueOf(data), true);
                    solve(cnt + 1, data, target);
                    wordMap.put(String.valueOf(data), false);
                }
                data[i] = tmp;
            }
        }
    }
}