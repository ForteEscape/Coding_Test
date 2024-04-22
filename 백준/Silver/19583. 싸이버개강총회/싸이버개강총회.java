import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] standardTime = new int[3];
		
		for(int i = 0; i < 3; i++) {
			String data = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(data, ":");
			
			int hour = Integer.parseInt(st2.nextToken());
			int min = Integer.parseInt(st2.nextToken());
			
			standardTime[i] = hour * 60 + min;
		}
		String line = null;
		
		Map<String, Integer> enterenceMap = new HashMap<>();
		
		int ans = 0;
		while((line = (br.readLine())) != null) {
			st = new StringTokenizer(line);
			
			String time = st.nextToken();
			String name = st.nextToken();
			
			StringTokenizer st2 = new StringTokenizer(time, ":");
			int hour = Integer.parseInt(st2.nextToken());
			int min = Integer.parseInt(st2.nextToken());
			
			int recordTime = hour * 60 + min;
			
			if(recordTime <= standardTime[0]) {
				enterenceMap.put(name, recordTime);
			} else if(recordTime > standardTime[0] && recordTime >= standardTime[1] && recordTime <= standardTime[2]) {
				if(enterenceMap.containsKey(name)) {
					ans++;
					enterenceMap.remove(name);
				}
			}

		}
		
		System.out.println(ans);
	}
 }