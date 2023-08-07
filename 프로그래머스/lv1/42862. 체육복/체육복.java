import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();
        
        for(int element: lost){
            lostSet.add(element);
        }
        
        for(int element: reserve){
            if(lostSet.contains(element)){
                lostSet.remove(element);
            } else{
                reserveSet.add(element);
            }
        }
        
        for(int element: reserveSet){
            if(lostSet.contains(element - 1)){
                lostSet.remove(element - 1);
            } else if(lostSet.contains(element + 1)){
                lostSet.remove(element + 1);
            }
        }
        
        return n - lostSet.size();
    }
}