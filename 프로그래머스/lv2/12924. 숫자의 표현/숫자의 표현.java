class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int[] prefixSum = new int[n + 1];
        
        for(int i = 1; i < n + 1; i++){
            prefixSum[i] = prefixSum[i - 1] + i;
        }
        
        int p1 = 0;
        int p2 = 1;
        while(p1 != prefixSum.length && p2 != prefixSum.length){
            int result = prefixSum[p2] - prefixSum[p1];
            
            if(result < n){
                p2++;
            } else if(result == n){
                answer++;
                p1++;
            } else{
                p1++;
            }
        }
        
        return answer;
    }
}