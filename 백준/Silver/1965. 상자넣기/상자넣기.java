import java.io.*;
import java.util.*;

public class Main {
	
	static int[] lis;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] data = new int[N];
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		lis = new int[N];
		lis[0] = data[0];
		
		int idx = 0;
		for(int i = 1; i < N; i++) {
			if(lis[idx] < data[i]) {
				lis[idx + 1] = data[i];
				idx++;
			} else {
				int temp = binarySearch(idx, data[i]);
				lis[temp] = data[i];
			}
		}
		
		System.out.println(idx + 1);
	}
	
	public static int binarySearch(int idx, int key) {
		int left = 0;
		int right = idx;
		
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