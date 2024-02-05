import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] data;
	static StringTokenizer st;
	static int K;
	static List[] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		data = new int[(1 << K) - 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < (1 << K) - 1; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		result = new List[K + 1];
		for(int i = 1; i <= K; i++) {
			result[i] = new ArrayList<Integer>();
		}
		
		solve(0, data.length - 1, 1);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<= K; i++) {
			for(int j = 0; j < result[i].size(); j++) {
				sb.append(result[i].get(j)).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void solve(int start, int end, int floor) {
		if(floor > K) {
			return;
		}
		
		int mid = (start + end) / 2;
		result[floor].add(data[mid]);
		
		solve(start, mid - 1, floor + 1);
		solve(mid + 1, end, floor + 1);
	}
}