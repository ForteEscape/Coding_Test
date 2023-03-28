import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        
        for(int i = 0; i < 9; i++){
            list.add(new HashSet<>());
        }
        
        list.get(1).add(N);
        
        for(int i = 2; i <9; i++){
            Set<Integer> countSet = list.get(i);
            
            for(int j = 1; j <= i; j++){
                Set<Integer> preSet = list.get(j);
                Set<Integer> postSet = list.get(i - j);
                
                for(int preElement: preSet){
                    for(int postElement: postSet){
                        countSet.add(preElement + postElement);
                        countSet.add(preElement - postElement);
                        countSet.add(preElement * postElement);
                    
                        if(preElement != 0 && postElement != 0){
                            countSet.add(preElement / postElement);
                        }
                    }
                }
            }
            
            countSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            
        }
        
        for(Set<Integer> sub : list){
            if(sub.contains(number)){
                return list.indexOf(sub);
            }
        }
        
        return -1;
    }
}