import java.util.*;

class Solution {
    
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        String color = board[h][w];
        
        for(int i = 0; i < 4; i++) {
            int ny = h + dy[i];
            int nx = w + dx[i];
            
            if(isUnreachable(board, ny, nx)) {
                continue;
            }
            
            if(color.equals(board[ny][nx])) {
                answer++;
            }
        }
        
        return answer;
    }
    
    static boolean isUnreachable(String[][] board, int y, int x) {
        return y < 0 || y >= board.length || x < 0 || x >= board[0].length;
    }
}