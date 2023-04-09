import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        long[] prefixSum = new long[sequence.length + 1];
        int minLength = Integer.MAX_VALUE;
        
        for(int i = 1; i < sequence.length + 1; i++){
            prefixSum[i] = prefixSum[i - 1] + sequence[i - 1];
        }
        
        int start = 0;
        int end = 1;
        
        while(start < prefixSum.length && end < prefixSum.length){
            long result = prefixSum[end] - prefixSum[start];
            
            if(result < k){
                end++;
            } else if(result == k){
                int seqLength = end - start;
                
                if(minLength > seqLength){
                    minLength = seqLength;
                    answer[0] = start;
                    answer[1] = end - 1;
                }
                start++;
            } else{
                start++;
            }
        }
        
        return answer;
    }
}