import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] port;
	static int[] lis;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		port = new int[N];
		lis = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			port[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		lis[0] = port[0];
		for(int i = 1; i < N; i++) {
			if(lis[idx] < port[i]) {
				lis[idx + 1] = port[i];
				idx++;
			} else {
				int insertIdx = binarySearch(0, idx, port[i]);
				lis[insertIdx] = port[i];
			}
		}
		
		System.out.println(idx + 1);
	}
	
	public static int binarySearch(int left, int right, int key) {
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(lis[mid] < key) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return right;
	}
	
}