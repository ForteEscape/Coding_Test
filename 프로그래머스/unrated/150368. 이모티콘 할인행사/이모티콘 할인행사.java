import java.util.*;

class Solution {
    static int[] discountData = {10, 20, 30, 40};
    static ArrayList<int[]> result;
    
    public static void solve(int[][] users, int[] emoticons, int length, int[] discount){
        if(length == emoticons.length){
            int plusCnt = 0;
            int benefit = 0;
            
            for(int i = 0; i < users.length; i++){
                int sum = 0;
                
                for(int j = 0; j < emoticons.length; j++){
                    if(users[i][0] <= discount[j]){
                        sum += emoticons[j] / 100 * (100 - discount[j]);
                    }
                }
                
                if(sum >= users[i][1]){
                    plusCnt++;
                } else{
                    benefit += sum;
                }
            }
            
            result.add(new int[]{plusCnt, benefit});
            return;
        }
        
        for(int i = 0; i < 4; i++){
            discount[length] = discountData[i];
            solve(users, emoticons, length + 1, discount);
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        result = new ArrayList<>();
        result.add(new int[]{0, 0});
        solve(users, emoticons, 0, new int[emoticons.length]);
        
        Collections.sort(result, (x, y) -> (y[0] - x[0]) == 0 ? y[1] - x[1] : y[0] - x[0]);
        
        return result.get(0);
    }
}