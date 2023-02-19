class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        int result_denom = denom1 * denom2;
        int result_numer = numer1 * denom2 + numer2 * denom1;
        int pivot = result_denom < result_numer ? result_denom : result_numer;
        int divisor = 1;
        
        for(int i = 2; i <= pivot; i++){
            if(result_denom % i == 0 && result_numer % i == 0){
                divisor = i;
            } 
        }
        
        answer[0] = result_numer / divisor;
        answer[1] = result_denom / divisor;
        
        return answer;
    }
}