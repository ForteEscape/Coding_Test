import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
            int[][] board = new int[N][N];
            
            for(int i = 0; i < N; i++){
                String data = br.readLine();
                for(int j = 0; j < N; j++){
                    board[i][j] = data.charAt(j) - '0';
                }
            }
            
            int mid = N / 2;
            int sum = 0;
            int start = mid;
            int end = mid;
            
            for(int i = 0; i <= N / 2; i++){
                for(int j = start; j <= end; j++){
                    sum += board[i][j];
                }
                
                start--;
                end++;
            }
            
            start += 2;
            end -= 2;
            
            for(int i = N / 2 + 1; i < N; i++){
                for(int j = start; j <= end; j++){
                    sum += board[i][j];
                }
                
                start++;
                end--;
            }
            
            System.out.println("#" + test_case + " " + sum);
		}
	}
}