import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int element: scoville){
            pq.offer(element);
        }
        
        while(pq.peek() < K){
            answer++;
            
            if(pq.size() == 1){
                return -1;
            }
            
            int a = pq.poll();
            int b = pq.poll() * 2;
            
            pq.offer(a + b);
        }
        
        return answer;
    }
}