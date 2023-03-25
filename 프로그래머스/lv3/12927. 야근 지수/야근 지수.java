import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (y - x));
        long sum = 0;
        
        for(int i = 0; i < works.length; i++){
            pq.offer(works[i]);
            sum += works[i];
        }
        
        if(sum <= n){
            return 0;
        }
        
        while(n > 0){
            n--;
            pq.offer(pq.poll() - 1);
        }
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}