import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Deque<Integer> bucket = new ArrayDeque<>();
        Deque<Integer>[] boardStack = new ArrayDeque[board.length];
        
        for(int i = 0; i < board.length; i++){
            boardStack[i] = new ArrayDeque<>();
        }
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] != 0){
                    boardStack[j].addFirst(board[i][j]);
                }
            }
        }
        
        for(int element: moves){
            if(boardStack[element - 1].isEmpty()){
                continue;
            }
            
            if(bucket.isEmpty()){
                bucket.addLast(boardStack[element - 1].pollLast());
            } else{
                int choiceElement = boardStack[element - 1].pollLast();
                
                if(bucket.peekLast() == choiceElement){
                    bucket.pollLast();
                    answer++;
                } else{
                    bucket.addLast(choiceElement);
                }
            }
        }
        
        return answer * 2;
    }
}