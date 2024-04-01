import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static class Gift {
		int price;
		int value;
		
		Gift(int price, int value) {
			this.price = price;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Gift [price=" + price + ", value=" + value + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		Gift[] gifts = new Gift[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int price = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			gifts[i] = new Gift(price, value);
		}
		
		int start = 0;
		int end = 1;
		
		Arrays.sort(gifts, (g1, g2) -> g1.price - g2.price);
		long curr = gifts[start].value;
		long ans = curr;
		
		while(end < N) {
			if(gifts[end].price - gifts[start].price < D) {
				curr += gifts[end].value;
				end++;
			} else {
				curr -= gifts[start].value;
				start++;
			}
			
			ans = Math.max(ans, curr);
		}
		
		System.out.println(ans);
	}
}