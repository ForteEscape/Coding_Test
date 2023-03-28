class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] map = new int[n][m];
        
        for(int[] element: puddles){
            map[element[1] - 1][element[0] - 1] = 1;
        }
        
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        
        //edge
        for(int i = 1; i < m; i++){
            if(map[0][i] != 1){
                dp[0][i] = dp[0][i - 1];
            }
        }
        
        for(int i = 1; i < n; i++){
            if(map[i][0] != 1){
                dp[i][0] = dp[i - 1][0];
            }
        }
        
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(map[i][j] != 1){
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
                }
            }
        }
        
        return dp[n - 1][m - 1];
    }
}