class Solution {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        solve(numbers, 0, target, 0);
        int ans = answer;
        
        return ans;
    }
    
    public void solve(int[] numbers, int idx, int target, int result){
        if(idx == numbers.length){
            if(result == target){
                answer++;
            }
            return;
        }
        
        int tmp = numbers[idx];
        // + 붙일 때
        solve(numbers, idx + 1, target, result + tmp);
        solve(numbers, idx + 1, target, result + (tmp * -1));
    }
}