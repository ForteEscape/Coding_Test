import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] price = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			price[i] = Integer.parseInt(br.readLine());
		}
		
		int[] weight = new int[M + 1];
		for(int i = 1; i <= M; i++) {
			weight[i] = Integer.parseInt(br.readLine());
		}
		
		int[] parkArea = new int[N + 1];
		
		Deque<Integer> waitQueue = new ArrayDeque<>();
		int ans = 0;
		for(int i = 0; i < 2 * M; i++) {
			int carNum = Integer.parseInt(br.readLine());
			
			if(carNum > 0) {
				int idx = 1;
				boolean isParkable = false;
				for(idx = 1; idx <= N; idx++) {
					if(parkArea[idx] == 0) {
						parkArea[idx] = carNum;
						isParkable = true;
						break;
					}
				}
				
				if(isParkable) {
					ans += price[idx] * weight[carNum];
				} else {
					waitQueue.addLast(carNum);
				}
			} else {
				int idx = 1;
				for(idx = 1; idx <= N; idx++) {
					if(parkArea[idx] == Math.abs(carNum)) {
						parkArea[idx] = 0;
						break;
					}
				}
				
				if(!waitQueue.isEmpty()) {
					parkArea[idx] = waitQueue.pollFirst();
					ans += price[idx] * weight[parkArea[idx]];
				}
			}
		}
		
		System.out.println(ans);
	}
}