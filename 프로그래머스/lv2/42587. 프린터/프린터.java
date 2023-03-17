import java.util.*;

class Paper{
    int priority;
    int location;
    
    Paper(int priority, int location){
        this.priority = priority;
        this.location = location;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        LinkedList<Paper> printer = new LinkedList<>();
        
        for(int i = 0; i < priorities.length; i++){
            printer.addLast(new Paper(priorities[i], i));
        }
        
        while(!printer.isEmpty()){
            Paper paper = printer.peekFirst();
            boolean canPrint = true;
            
            for(Paper otherPaper: printer){
                if(otherPaper.priority > paper.priority){
                    canPrint = false;
                    break;
                }
            }
            
            if(canPrint){
                if(paper.location == location){
                    return answer;
                } else{
                    answer++;
                    printer.pollFirst();
                }
            } else{
                printer.addLast(printer.pollFirst());
            }
        }
        
        return -1;
    }
}