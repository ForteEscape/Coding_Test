import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static long[] treeData;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int height = (int)Math.ceil(Math.log(N) / Math.log(2));
		
		treeData = new long[(1 << (height + 1))];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int q = Integer.parseInt(st.nextToken());
			
			if(q == 0) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				if(start > end) {
					int temp = start;
					start = end;
					end = temp;
				}
				
				sb.append(sum(start, end, 1, 1, N)).append("\n");
			} else {
				int idx = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				
				update(1, idx, k, 1, N);
			}
		}
		
		System.out.println(sb);
	}
	
	public static long sum(int start, int end, int node, int left, int right) {
		// 범위 내부에 완전히 속하는 경우 그대로 반환한다.
		if(start <= left && right <= end) {
			return treeData[node];
		}
		
		// 범위가 아예 겹치지 않는 경우 탐색을 그냥 종료시킨다.
		if(start > right || end < left) {
			return 0;
		}
		
		// 범위가 일정 부분 겹치는 경우에는 다시 재귀로 들어간다.
		int mid = (left + right) / 2;
		return sum(start, end, node * 2, left, mid) + 
				sum(start, end, node * 2 + 1, mid + 1, right);
	}
	
	public static void update(int node, int idx, int value, int left, int right) {
		if(left == right) {
			treeData[node] = value;
			return;
		}
		
		int mid = (left + right) / 2;
		
		if(left <= idx && idx <= mid) {
			update(node * 2, idx, value, left, mid);
		} else {
			update(node * 2 + 1, idx, value, mid + 1, right);
		}
		
		treeData[node] = treeData[node * 2] + treeData[node * 2 + 1];
	}
}