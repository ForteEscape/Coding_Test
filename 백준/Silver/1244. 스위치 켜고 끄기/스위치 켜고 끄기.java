import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] switchData = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			switchData[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {
				for(int j = 1 * num; j <= N; j += num) {
					switchData[j] = 1 - switchData[j];
				}
			} else {
				for(int j = 1;; j++) {
					int left = num - j;
					int right = num + j;
					
					if(left <= 0 || right > N) {
						break;
					}
					
					if(switchData[left] != switchData[right]) {
						break;
					}
					
					switchData[left] = 1 - switchData[left];
					switchData[right] = 1 - switchData[right];
				}
				
				switchData[num] = 1 - switchData[num];
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++ ) {
			if(i % 20 == 0) {
				sb.append(switchData[i]).append("\n");
			} else {
				sb.append(switchData[i]).append(" ");
			}
		}
		System.out.println(sb);
	}

	
}