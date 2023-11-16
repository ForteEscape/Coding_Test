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
			String data = br.readLine();
            Set<String> set = new HashSet<>();
            Map<Character, Integer> map = new HashMap<>();
            map.put('S', 13);
            map.put('H', 13);
            map.put('D', 13);
            map.put('C', 13);
            
            boolean flag = false;
            while(data.length() > 1){
                String element = data.substring(0, 3);
                String remain = data.substring(3, data.length());
                
                if(set.contains(element)){
                    flag = true;
                    break;
                }
                
                map.put(element.charAt(0), map.get(element.charAt(0)) - 1);
                set.add(element);
                
                data = remain;
            }
            
            if(flag){
                System.out.println("#" + test_case + " ERROR");
            } else {
                System.out.println("#" + test_case + " " + map.get('S') + " " + map.get('D') + " " + map.get('H') + " " + map.get('C'));
            }
		}
	}
}