import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] data;
	static int[] lis;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		data = new int[N];
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		lis = new int[N];
		lis[0] = data[0];
		int idx = 0;
		for(int i = 1; i < N; i++) {
			if(data[i] > lis[idx]) {
				lis[idx + 1] = data[i];
				idx++;
			} else {
				int insertIdx = binarySearch(0, idx, data[i]);
				lis[insertIdx] = data[i];
			}
		}
		
		System.out.println(idx + 1);
	}
	
	public static int binarySearch(int start, int end, int key) {
		
		while(start < end) {
			int mid = (start + end) / 2;
			
			if(lis[mid] < key) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return end;
	}
}