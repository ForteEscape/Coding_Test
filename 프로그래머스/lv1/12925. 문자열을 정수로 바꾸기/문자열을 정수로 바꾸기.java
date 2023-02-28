class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i = s.length() - 1, j = 1; i >= 0; i--, j *= 10){
            if(s.charAt(i) == '-'){
                answer *= -1;
                continue;
            } else if(s.charAt(i) == '+'){
                continue;
            }
            
            answer += (s.charAt(i) - '0') * j;
        }
        
        return answer;
    }
}