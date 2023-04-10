import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] newBoard = new char[m][n];
        
        for(int i = 0; i < m; i++){
            newBoard[i] = board[i].toCharArray();
        }
        
        
        while(true){
            int result = solve(m, n, newBoard);
            if(result == 0) break;
            
            answer += result;
        }
        
        return answer;
    }
    
    
    public static int solve(int m, int n, char[][] board){
        boolean[][] visited = new boolean[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char character = board[i][j];
                if(character == ' ') continue;
                
                int ny = i + 1;
                int nx = j + 1;
                
                if(ny >= m || nx >= n){
                    continue;
                }
                
                if(character == board[i][nx] && character == board[ny][j] && character == board[ny][nx]){
                    visited[i][j] = true;
                    visited[i][nx] = true;
                    visited[ny][j] = true;
                    visited[ny][nx] = true;
                }
            }
        }
        
        int cnt = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]){
                    cnt++;
                    board[i][j] = ' ';
                }
            }
        }
        
        /*
        System.out.println("before");
        for(int i = 0; i < m; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("");
        */
        
        for(int i = m - 1; i >= 0; i--){
            for(int j = 0; j < n; j++){
                if(board[i][j] == ' '){
                    for(int k = i - 1; k >= 0; k--){
                        if(board[k][j] != ' '){
                            board[i][j] = board[k][j];
                            board[k][j] = ' ';
                            break;
                        }
                    }
                }
            }
        }
        
        /*
        System.out.println("After");
        for(int i = 0; i < m; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("");
        */
        
        return cnt;
    }
    
}