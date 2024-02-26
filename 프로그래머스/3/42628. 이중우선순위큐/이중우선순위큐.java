import java.util.*;

class Solution {
    
    static class Data implements Comparable<Data>{
        int idx;
        int data;
        
        Data(int idx, int data) {
            this.idx = idx;
            this.data = data;
        }
        
        @Override
        public int compareTo(Data o) {
            return this.data - o.data;
        }
    }
    
    static TreeSet<Data> set;
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        set = new TreeSet<>();
        for(int i = 0; i < operations.length; i++) {
            StringTokenizer st=  new StringTokenizer(operations[i]);
            
            char query = st.nextToken().charAt(0);
            if(query == 'I') {
                int data = Integer.parseInt(st.nextToken());
                set.add(new Data(i, data));
            } else if(query == 'D') {
                int oper = Integer.parseInt(st.nextToken());
                
                if(oper < 0) {
                    set.pollFirst();
                } else {
                    set.pollLast();
                }
            }
        }
        
        if(set.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = set.last().data;
            answer[1] = set.first().data;
        }
        
        return answer;
    }
}