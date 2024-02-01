import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int[] data;
	static int[] lis;
	static int[] preIdx;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		data = new int[N];
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		lis = new int[N];
		preIdx = new int[N];
		
		lis[0] = data[0];
		int idx = 0;
		for(int i = 1; i < N; i++) {
			if(data[i] > lis[idx]) {
				lis[idx + 1] = data[i];
				idx++;
				preIdx[i] = idx;
			} else {
				int insertIdx = binarySearch(0, idx, data[i]);
				lis[insertIdx] = data[i];
				preIdx[i] = insertIdx;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(idx + 1).append("\n");
		
		Deque<Integer> stack = new ArrayDeque<>();
		int index = idx;
		for(int i = N - 1; i >= 0; i--) {
			if(preIdx[i] == index) {
				stack.addLast(data[i]);
				index--;
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pollLast()).append(" ");
		}
		System.out.println(sb);
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