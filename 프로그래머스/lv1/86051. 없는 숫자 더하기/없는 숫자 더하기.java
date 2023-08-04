class Solution {
    public int solution(int[] numbers) {
        int answer = 45;
        
        for(int element: numbers){
            answer -= element;
        }
        
        return answer;
    }
}