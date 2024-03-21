import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static Map<Integer, Integer> map;
	static int[] arr;
	static long[] tree;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		map = new HashMap<>();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N + 1];
			tree = new long[4 * N];
			map.clear();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				int element = Integer.parseInt(st.nextToken());
				map.put(element, i);
			}
			
			long ans = 0;
			for(int i = 1; i <= N; i++) {
				ans += query(1, 1, N, map.get(arr[i]) + 1, N);
				update(1, 1, N, map.get(arr[i]), 1);
			}
			
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	static void update(int node, int start, int end, int idx, int val) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			tree[node] += val;
			return;
		}
		
		int mid = (start + end) >> 1;
		update(node << 1, start, mid, idx, val);
		update(node << 1 | 1, mid + 1, end, idx, val);
		
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
	
	static long query(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) >> 1;
		
		return query(node << 1, start, mid, left, right) + 
				query(node << 1 | 1, mid + 1, end, left, right);
	}
}