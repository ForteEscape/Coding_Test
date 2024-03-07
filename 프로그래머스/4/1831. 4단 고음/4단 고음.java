class Solution {
    static int cnt;
    
    public int solution(int n) {
        cnt = 0;
        solve(n - 2, 2);
        return cnt;
    }
    
    public static void solve(int cur, int plus) {
        if(cur < 3 || ((int)(Math.log(cur) / Math.log(3)) << 1) < plus) {
            return;
        }
        
        if(cur == 3) {
            if(plus == 2) cnt++;
            return;
        }
        
        if(cur % 3 == 0 && plus > 1) {
            solve(cur / 3, plus - 2);
        }
        solve(cur - 1, plus + 1);
    }
}