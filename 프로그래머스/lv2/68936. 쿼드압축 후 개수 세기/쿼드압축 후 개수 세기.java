class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        divide(arr, 0, arr.length - 1, 0, arr.length - 1, answer);
        
        return answer;
    }
    
    public static void divide(int[][] arr, int startX, int endX, int startY, int endY, int[] answer){
        if(startX == endX && startY == endY){
            answer[arr[startY][startX]]++;
            return;
        }
        
        if(chk(arr, startX, endX, startY, endY)){
            answer[arr[startY][startX]]++;
            return;
        }
        
        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;
        
        divide(arr, startX, midX, startY, midY, answer);
        divide(arr, midX + 1, endX, startY, midY, answer);
        divide(arr, startX, midX, midY + 1, endY, answer);
        divide(arr, midX + 1, endX, midY + 1, endY, answer);
    }
    
    public static boolean chk(int[][] arr, int startX, int endX, int startY, int endY){
        int element = arr[startY][startX];
        
        for(int i = startY; i <= endY; i++){
            for(int j = startX; j <= endX; j++){
                if(arr[i][j] != element){
                    return false;
                }
            }
        }
        
        return true;
    }
}