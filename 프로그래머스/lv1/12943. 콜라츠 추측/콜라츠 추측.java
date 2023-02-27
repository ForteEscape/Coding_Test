class Solution {
    public int solution(int num) {
        int answer = 0;
        int cnt = 0;
        long temp = num;
        
        if(num == 1){
            return 0;
        }
        
        while(temp != 1){
            if(answer > 500){
                answer = -1;
                break;
            }
            
            if(temp % 2 == 0){
                temp /= 2;
            } else{
                temp = temp * 3 + 1;
            }
            
            answer++;
        }
        
        return answer;
    }
}