class Solution
{
    public int solution(int n, int a, int b)
    {
        int ans = 1;
        int round = 0;
        int tmp = 1;
        
        for(tmp = 1; tmp <= n; tmp *= 2) {
            round++;
        }
        
        if(a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        return solve(round, 1, n, a, b);
    }
    
    static int solve(int round, int start, int end, int a, int b) {
        if(a > end || a < start) {
            return 21;
        }
        
        if((start <= a && a <= end) && b > end) {
            return round;
        }
        
        int mid = (start + end) >> 1;
        
        int tmp1 = solve(round - 1, start, mid, a, b);
        int tmp2 = solve(round - 1, mid + 1, end, a, b);
        
        return Math.min(tmp1, tmp2);
    }
}