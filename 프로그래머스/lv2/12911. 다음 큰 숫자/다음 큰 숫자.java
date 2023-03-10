class Solution {
    public int solution(int n) {
        int answer = 0;
        
        answer = n;
        int cnt = Integer.bitCount(n);
        
        while(true){
            ++answer;
            
            if(Integer.bitCount(answer) == cnt){
                break;
            }
        }
        
        return answer;
    }
}