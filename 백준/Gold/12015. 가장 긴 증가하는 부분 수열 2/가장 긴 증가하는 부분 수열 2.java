import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int nums[];
	static int lis[];
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		lis = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		lis[0] = nums[0];
		
		int lisIdx = 0;
		
		for(int i = 1; i < N; i++) {
			// 만약 현재 값이 lis 배열의 마지막 값보다 크다면 그대로 넣어주면 된다.
			if(lis[lisIdx] < nums[i]) {
				lis[lisIdx + 1] = nums[i];
				lisIdx++;
			} else { // 더 작다면 해당 값을 넣을 위치를 잡아야한다.
				int idx = binarySearch(0, lisIdx, nums[i]);
				lis[idx] = nums[i];
			}
		}
		
		System.out.println(lisIdx + 1);
	}
	
	public static int binarySearch(int left, int right, int key) {
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(lis[mid] < key) { // lis 원소가 현재 값보다 작거나 같다면 삽입 가능
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return right;
	}

}