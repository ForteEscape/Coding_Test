import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < players.length; i++){
            map.put(players[i], i);
        }
        
        for(String calledPlayer: callings){
            int rank = map.get(calledPlayer);
            
            String fowardCalledPlayer = players[rank - 1];
            swap(players, rank, rank - 1);
            
            map.put(fowardCalledPlayer, rank);
            map.put(calledPlayer, rank - 1);
        }
        
        return players;
    }
    
    public void swap(String[] players, int idx1, int idx2){
        String temp = players[idx1];
        players[idx1] = players[idx2];
        players[idx2] = temp;
    }
}