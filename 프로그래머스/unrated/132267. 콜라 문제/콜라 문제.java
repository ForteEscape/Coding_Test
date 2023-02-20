class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while(a <= n){
            answer += b * (n / a);
            n = (n - a * (n / a)) + (b * (n / a));
        }
        
        return answer;
    }
}