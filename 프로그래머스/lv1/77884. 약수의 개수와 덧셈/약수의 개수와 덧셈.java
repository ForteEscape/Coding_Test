class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        for(int i = left; i <= right; i++){
            if(i == 1){
                answer -= i;
            } else if(i == 2 || i == 3){
                answer += i;
            } else if(Math.sqrt(i) % 1 == 0){
                answer -= i;
            } else{
                answer += i;
            }
        }
        
        return answer;
    }
}