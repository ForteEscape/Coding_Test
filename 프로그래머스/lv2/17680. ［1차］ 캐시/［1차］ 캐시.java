import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();
        
        for(String city : cities){
            if(cacheSize == 0){
                answer += 5;
                continue;
            }
            
            String cityToLow = city.toLowerCase();
            
            if(cache.contains(cityToLow)){
                answer++;
                cache.remove(cityToLow);
                cache.addLast(cityToLow);
            } else{
                if(cache.size() < cacheSize){
                    cache.addLast(cityToLow);
                } else{
                    cache.pollFirst();
                    cache.addLast(cityToLow);
                }
                
                answer += 5;
            }
        }
        
        return answer;
    }
}