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
			StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int ans = 0;
            
            for(int i = start; i <= end; i++){
                if((int)(Math.sqrt(i)) == Math.sqrt(i)){
                    String str = String.valueOf(i);
                    String str2 = String.valueOf((int)Math.sqrt(i));
                    
                    if(str.length() == 1){
                        ans++;
                        continue;
                    }
                    
                    if(isPalimdrom(str) && isPalimdrom(str2)){
                        ans++;
                    }
                }
            }
            System.out.println("#" + test_case + " " + ans);
		}
	}
    
    public static boolean isPalimdrom(String str){
        for(int j = 0; j < str.length() / 2; j++){
            if(str.charAt(j) != str.charAt(str.length() - 1 - j)){
                return false;
            }
        }
        
        return true;
    }
}