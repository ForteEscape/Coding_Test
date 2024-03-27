import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int[] lis;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[] data = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		lis = new int[N];
		lis[0] = data[0];
		int idx = 0;
		
		for(int i = 1; i < N; i++) {
			if(data[i] > lis[idx]) {
				lis[++idx] = data[i];
			} else {
				int tmp = binarySearch(0, idx, data[i]);
				lis[tmp] = data[i];
			}
		}
		
		System.out.println(N - (idx + 1));
	}
	
	static int binarySearch(int start, int end, int key) {
		int left = start;
		int right = end;
		
		while(left < right) {
			int mid = (left + right) >> 1;
		
			if(lis[mid] > key) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
}