import java.util.*;

class Solution {
    static class Location{
        int y;
        int x;
        
        Location(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static int N, M;
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        char[][] newMap = new char[N][M];
        
        for(int i = 0; i < N; i++){
            newMap[i] = maps[i].toCharArray();
        }
        
        visited = new boolean[N][M];
        Deque<Location> queue = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                int duration = 0;
                
                if(newMap[i][j] != 'X' && !visited[i][j]){
                    duration += newMap[i][j] - '0';
                    visited[i][j] = true;
                    queue.addLast(new Location(i, j));
                }
                
                while(!queue.isEmpty()){
                    Location cur = queue.pollFirst();
                    
                    for(int dir = 0; dir < 4; dir++){
                        int ny = cur.y + dy[dir];
                        int nx = cur.x + dx[dir];
                        
                        if(ny < 0 || ny >= N || nx < 0 || nx >= M){
                            continue;
                        }
                        
                        if(newMap[ny][nx] != 'X' && !visited[ny][nx]){
                            visited[ny][nx] = true;
                            duration += newMap[ny][nx] - '0';
                            queue.addLast(new Location(ny, nx));
                        }
                    }
                }
                
                if(duration != 0){
                    result.add(duration);
                }
            }
        }
        
        if(result.size() == 0){
            int[] ans = {-1};
            return ans;
        }
        
        Collections.sort(result);
        int[] answer = new int[result.size()];
        int idx = 0;
        
        for(int element: result){
            answer[idx++] = element;
        }
        
        return answer;
    }
}