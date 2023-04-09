class Solution {
    static int[] dx = {0, 0, 1, 1, -1, -1, 1, -1};
    static int[] dy = {1, -1, 1, -1, 1, -1, 0, 0};
    
    public int solution(int[][] board) {
        int answer = 0;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == 1){
                    marking(board, i, j);
                }
            }
        }
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public static void marking(int[][] board, int y, int x){
        for(int dir = 0; dir < 8; dir++){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            
            if(ny < 0 || ny >= board.length || nx < 0 || nx >= board.length){
                continue;
            }
            
            if(board[ny][nx] == 0){
                board[ny][nx] = 2;
            }
        }
    }
}