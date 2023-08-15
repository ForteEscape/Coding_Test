import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        
        for(int i = 0; i < score.length; i++){
            pq.offer(score[i]);
            
            List<Integer> pollList = new ArrayList<>();
            
            if(pq.size() <= k){
                while(!pq.isEmpty()){
                    pollList.add(pq.poll());
                }
                
                answer[i] = pollList.get(pollList.size() - 1);
            } else{
                for(int j = 0; j < k - 1; j++){
                    pollList.add(pq.poll());
                }
                
                answer[i] = pq.peek();
            }
            
            for(int element: pollList){
                pq.offer(element);
            }
        }
        return answer;
    }
}