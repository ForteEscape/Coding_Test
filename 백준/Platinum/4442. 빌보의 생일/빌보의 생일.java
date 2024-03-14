import java.util.*;
import java.io.*;

public class Main {
	
	static String[] name;
	static long[] tree;
	static Map<String, Integer> map;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) return;
			
			name = new String[N + 1];
			tree = new long[4 * N];
			map = new HashMap<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				name[i] = st.nextToken();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				String nameData = st.nextToken();
				map.put(nameData, i);
			}
			
			long ans = 0;
			for(int i = 1; i <= N; i++) {
				ans += query(1, 1, N, map.get(name[i]) + 1, N);
				update(1, 1, N, map.get(name[i]), 1);
			}
			
			System.out.println(ans);
		}
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
		
		if(idx <= mid) {
			update(node << 1, start, mid, idx, val);
		} else {
			update((node << 1) | 1, mid + 1, end, idx, val);
		}
		
		tree[node] = tree[node << 1] + tree[(node << 1) | 1];
	}
	
	static long query(int node, int start, int end, int left, int right) {
		if(end < left || start > right) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) >> 1;
		
		return query(node << 1, start, mid, left, right) + 
				query((node << 1) | 1, mid + 1, end, left, right);
	}
}