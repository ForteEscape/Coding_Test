import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static Integer[][] data;
	static int[] lis;
	static int N;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		data = new Integer[N][2];
		lis = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(data, (o1, o2) -> o1[0] - o2[0]);
		
		int idx = 0;
		lis[0] = data[0][1];
		for(int i = 1; i < N; i++) {
			if(lis[idx] < data[i][1]) {
				lis[idx + 1] = data[i][1];
				idx++;
			} else {
				int insertIdx = binarySearch(0, idx, data[i][1]);
				lis[insertIdx] = data[i][1];
			}
		}
		
		System.out.println(N - idx - 1);
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