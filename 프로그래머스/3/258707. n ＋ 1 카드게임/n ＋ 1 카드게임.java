import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        Set<Integer> currentSet = new HashSet<>();
        Set<Integer> appendSet = new HashSet<>();
        
        int idx = 0;
        for(idx = 0; idx < cards.length / 3; idx++) {
            currentSet.add(cards[idx]);
        }
        
        int answer = 1;
        while(idx < cards.length) {
            appendSet.add(cards[idx++]);
            appendSet.add(cards[idx++]);
            
            boolean find = false;
            int first = -1;
            int second = -1;
            
            // 현재 내가 들고 있는 카드 Set에서 n + 1을 찾은 경우
            for(int element : currentSet) {
                if(currentSet.contains(cards.length + 1 - element)) {
                    find = true;
                    first = element;
                    second = cards.length + 1 - element;
                    break;
                }
            }
            
            // n + 1을 찾지 못한 경우 내가 뽑은 카드 후보들 중 가져올 것을 탐색
            if(!find) {
                if(coin == 0) break;
                
                for(int element : currentSet) {
                    int key = cards.length + 1 - element;
                    
                    if(appendSet.contains(key) && coin > 0) {
                        coin--;
                        find = true;
                        
                        first = element;
                        second = key;
                        break;
                    }
                }
                
                if(!find){
                    for(int element : appendSet) {
                        int key = cards.length + 1 - element;
                        if(appendSet.contains(key) && coin > 1) {
                            coin -= 2;
                            find = true;
                        
                            first = element;
                            second = key;
                            break;
                        }
                    }
                }
            }
            
            if(!find) {
                break;
            } else {
                appendSet.remove(first);
                appendSet.remove(second);
                currentSet.remove(second);
                currentSet.remove(first);
            }
            
            answer++;
        }
        
        
        return answer;
    }
}