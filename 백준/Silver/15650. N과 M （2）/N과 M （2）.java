import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int R;
	static int[] element;
	static List<Integer> list = new ArrayList<>();
	 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		element = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			element[i] = i;
		}
		
		solve(0, 0, list);
	}
	
	public static void solve(int cur, int start, List<Integer> list) {
		if(cur == R) {
			StringBuilder sb = new StringBuilder();
			
			for(int element: list) {
				sb.append(element).append(" ");
			}
			
			System.out.println(sb);
			return;
		}
		
		for(int i = start + 1; i <= N; i++) {
			list.add(element[i]);
			solve(cur + 1, i, list);
			list.remove(list.size() - 1);
		}
	}
}