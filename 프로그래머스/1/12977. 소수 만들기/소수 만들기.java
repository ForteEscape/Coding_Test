import java.util.*;

class Solution {
    static int[] data;
    
    public int solution(int[] nums) {
        int answer = 0;
        getPrime();
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < nums.length - 2; i++) {
            for(int j = i + 1; j < nums.length - 1; j++) {
                for(int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    
                    if(data[sum] == 0) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
    
    static void getPrime() {
        data = new int[3001];
        
        for(int i = 2; i <= Math.sqrt(3000); i++) {
            if(data[i] == 1) {
                continue;
            }
            
            for(int j = i + i; j <= 3000; j += i) {
                data[j] = 1;
            }
        }
    }
}