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
            
            int ans = 0;
            for(int y = 0; y <= N; y++){
                for(int x = 0; x <= N; x++){
                    if(x * x + y * y <= N * N){
                        ans++;
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + ((ans * 4) - (4 * N) - 3));
		}
	}
}