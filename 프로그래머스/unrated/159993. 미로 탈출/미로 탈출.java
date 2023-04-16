import java.util.*;

class Solution {
    static class Location{
        int y;
        int x;
        int dist;
        
        Location(int y, int x, int dist){
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
    
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static String[] map;
    static boolean[][] visited;
    
    public int solution(String[] maps) {
        int answer = 0;
        int sy = 0, sx = 0;
        int ly = 0, lx = 0;
        int ey = 0, ex = 0;
        
        map = maps;
        
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j < maps[0].length(); j++){
                char data = maps[i].charAt(j);
                
                if(data == 'L'){
                    ly = i;
                    lx = j;
                } else if(data == 'S'){
                    sy = i;
                    sx = j;
                } else if(data == 'E'){
                    ey = i;
                    ex = j;
                }
            }
        }
        
        int laberDist = bfs(sy, sx, ly, lx);
        int endDist = bfs(ly, lx, ey, ex);
        
        if(laberDist == -1 || endDist == -1){
            return -1;
        }
        
        return laberDist + endDist;
    }
    
    public int bfs(int sy, int sx, int ey, int ex){
        visited = new boolean[map.length][map[0].length()];
        Deque<Location> queue = new ArrayDeque<>();
        queue.addLast(new Location(sy, sx, 0));
        
        while(!queue.isEmpty()){
            Location cur = queue.pollFirst();
            
            if(cur.y == ey && cur.x == ex){
                return cur.dist;
            }
            
            for(int dir = 0; dir < 4; dir++){
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];
                
                if(ny < 0 || ny >= map.length || nx < 0 || nx >= map[0].length()){
                    continue;
                }
                
                if(map[ny].charAt(nx) != 'X' && !visited[ny][nx]){
                    visited[ny][nx] = true;
                    queue.addLast(new Location(ny, nx, cur.dist + 1));
                }
            }
        }
        
        return -1;
    }
}