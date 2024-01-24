import java.util.*;
import java.io.*;

public class Main {
	
	static int[] ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Integer[] price = new Integer[M];
		for(int i = 0; i < M; i++) {
			price[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(price, (o1, o2) -> o2 - o1);
		
		int maxProfit = Integer.MIN_VALUE;
		int ansPrice = 0;
		
		for(int i = 0; i < M; i++) {
			int minPrice = price[i];
			int profit = 0;
			int cnt = 0;
			
			for(int j = 0; j < M; j++) {
				if(price[j] >= minPrice) {
					profit += minPrice;
					cnt++;
				}
				
				if(cnt == N) break;
			}
			
			if(maxProfit < profit) {
				maxProfit = profit;
				ansPrice = minPrice;
			}
		}
		
		System.out.println(ansPrice + " " + maxProfit);
	}
}
