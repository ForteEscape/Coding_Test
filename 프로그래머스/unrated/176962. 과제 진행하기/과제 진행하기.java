import java.util.*;

class Solution {
    static class Subject{
        String name;
        int time;
        int period;
        
        Subject(String name, int time, int period){
            this.name = name;
            this.time = time;
            this.period = period;
        }
    }
    
    public String[] solution(String[][] plans) {      
        Subject[] subjects = new Subject[plans.length];
        
        for(int i = 0; i < plans.length; i++){
            String[] rows = plans[i];
            
            String name = rows[0];
            StringTokenizer st = new StringTokenizer(rows[1], ":");
            
            int time = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            int period = Integer.parseInt(rows[2]);
            
            subjects[i] = new Subject(name, time, period);
        }
        
        Arrays.sort(subjects, (sub1, sub2) -> (sub1.time - sub2.time));
        Deque<Subject> holdingSubject = new ArrayDeque<>();
        
        int idx = 0;
        int ansIdx = 0;
        String[] answer = new String[plans.length];
        while(idx != plans.length){
            Subject subject = subjects[idx];
            
            String name = subject.name;
            int time = subject.time;
            int period = subject.period;
            
            int currentTime = time + period;
            int next = 0;
            
            if(plans.length - 1 != idx){
                next = subjects[idx + 1].time;
            }
            
            if(currentTime > next){
                holdingSubject.addLast(new Subject(subject.name, time, currentTime - next));
                currentTime = next;
                idx++;
                
                continue;
            }
            
            answer[ansIdx++] = name;
            
            while(!holdingSubject.isEmpty()){
                int remain = next - currentTime;
                Subject holdedSubject = holdingSubject.pollLast();
                
                int remainPeriod = holdedSubject.period;
                
                if(remain >= remainPeriod){
                    answer[ansIdx++] = holdedSubject.name;
                    currentTime += remainPeriod;
                } else{
                    holdingSubject.addLast(new Subject(holdedSubject.name, holdedSubject.time, holdedSubject.period - remain));
                    break;
                }
            }
            idx++;
        }
        
        while(!holdingSubject.isEmpty()){
            answer[ansIdx++] = holdingSubject.pollLast().name;
        }
        
        return answer;
    }
}