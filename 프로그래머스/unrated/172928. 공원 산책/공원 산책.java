import java.util.*;

class Solution {
    
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    static int[][] map;
    
    public int[] solution(String[] park, String[] routes) {
        Map<String, Integer> directionMap = new HashMap<>();
        
        directionMap.put("E", 2);
        directionMap.put("W", 3);
        directionMap.put("S", 0);
        directionMap.put("N", 1);
        
        map = new int[park.length][park[0].length()];
        int startX = -1;
        int startY = -1;
        
        
        for(int i = 0; i < park.length; i++){
            for(int j = 0; j < park[0].length(); j++){
                if(park[i].charAt(j) == 'S'){
                    startX = j;
                    startY = i;
                }
                
                if(park[i].charAt(j) == 'X'){
                    map[i][j] = 1;
                }
            }
        }
        
        for(String order: routes){
            StringTokenizer st = new StringTokenizer(order);
            
            String dir = st.nextToken();
            int movement = Integer.parseInt(st.nextToken());
            
            if(move(startX, startY, directionMap.get(dir), movement)){
                startX += dx[directionMap.get(dir)] * movement;
                startY += dy[directionMap.get(dir)] * movement;
            }
        }
        
        return new int[]{startY, startX};
    }
    
    public boolean move(int currentX, int currentY, int direction, int movement){
        if(currentX < 0 || currentX >= map[0].length || currentY < 0 || currentY >= map.length || map[currentY][currentX] == 1){
            return false;
        }
        
        if(movement == 0){
            return true;
        }
        
        return move(currentX + dx[direction], currentY + dy[direction], direction, movement - 1);
    }
}