import java.util.*;

class Work{
    int curProgress;
    int speed;
    
    Work(int curProgress, int speed){
        this.curProgress = curProgress;
        this.speed = speed;
    }
    
    public void addSpeed(){
        this.curProgress += speed;
    }
}

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        LinkedList<Work> queue = new LinkedList<>();
        for(int i = 0; i < speeds.length; i++){
            queue.addLast(new Work(progresses[i], speeds[i]));
        }
        
        while(!queue.isEmpty()){
            int cnt = 0;
            
            while(!queue.isEmpty() && queue.peekFirst().curProgress >= 100){
                queue.pollFirst();
                cnt++;
            }
            
            if(cnt != 0){
                ans.add(cnt);
            }
            
            if(queue.isEmpty()){
                break;
            }
            
            for(Work element: queue){
                element.addSpeed();
            }
        }
        
        int[] answer = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}