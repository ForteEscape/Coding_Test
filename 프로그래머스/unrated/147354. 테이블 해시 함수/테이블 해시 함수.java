import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        long answer = 0;
        
        Arrays.sort(data, (x, y) -> (x[col - 1] - y[col - 1]) == 0 ? (y[0] - x[0]) : (x[col - 1] - y[col - 1]));
        
        for(int i = row_begin; i <= row_end; i++){
            long sum = 0L;
            for(int j = 0; j < data[0].length; j++){
                sum += data[i - 1][j] % i;
            }
            
            answer ^= sum;
        }
        
        return (int)answer;
    }
}