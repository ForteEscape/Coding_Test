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
            int[] data = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i < N; i++){
                data[i] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(data);
            
            int ans = -1;
            for(int i = N - 1; i >= 1; i--){
                for(int j = N - 2; j >= 0; j--){
                    int res = data[i] * data[j];
                    String result = String.valueOf(res);
                    boolean flag = true;
                    
                    for(int k = 1; k < result.length(); k++){
                        if((result.charAt(k) - '0') - (result.charAt(k - 1) - '0') != 1){
                            flag = false;
                            break;
                        }
                    }
                    
                    if(flag){
                        ans = res;
                        break;
                    }
                }
                if(ans != -1) break;
            }
        
            System.out.println("#" + test_case + " " + ans);
		}
	}
}