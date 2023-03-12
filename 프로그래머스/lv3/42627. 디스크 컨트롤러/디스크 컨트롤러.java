import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (x, y) -> x[0] - y[0]); // sorting by requesting time
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        
        int curTime = 0;
        int idx = 0;
        int completeCnt = 0;
        
        while(completeCnt < jobs.length){
            while(idx < jobs.length && jobs[idx][0] <= curTime){
                pq.offer(jobs[idx++]);
            }
            
            if(pq.isEmpty()){
                curTime = jobs[idx][0];
            } else{
                int[] job = pq.poll();
                answer += curTime + job[1] - job[0];
                curTime += job[1];
                completeCnt++;
            }
        }
        
        return (int)Math.floor(answer / jobs.length);
    }
}