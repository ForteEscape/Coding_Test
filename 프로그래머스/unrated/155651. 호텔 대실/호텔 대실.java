import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] time = new int[book_time.length][2];
        
        for(int i = 0; i < book_time.length; i++){
            for(int j = 0; j < 2; j++){
                StringTokenizer st = new StringTokenizer(book_time[i][j], ":");
                
                int hour = Integer.parseInt(st.nextToken());
                int min = Integer.parseInt(st.nextToken());
                
                time[i][j] = hour * 60 + min;
            }
        }
        
        Arrays.sort(time, (x, y) -> (x[0] - y[0]));
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> (x[1] - y[1]));
        
        for(int i = 0; i < time.length; i++){
            System.out.println(time[i][0] + " " + time[i][1]);
        }
        
        for(int i = 0; i < time.length; i++){
            if(pq.isEmpty()){
                pq.offer(time[i]);
            } else{
                if(time[i][0] >= pq.peek()[1] + 10){
                    pq.poll();
                    pq.offer(time[i]);
                } else{
                    pq.offer(time[i]);
                }
            }
            
            answer = Math.max(answer, pq.size());
        }
        
        return answer;
    }
}