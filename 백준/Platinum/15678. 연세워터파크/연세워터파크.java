import java.util.*;
import java.io.*;

class Main {
	
	static int N, D;
	static long[] dp;
	static int[] v;
	static long[] tree;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		v = new int[N + 1];
		dp = new long[N + 1];
		tree = new long[4 * N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		update(1, 1, N, 1, v[1]);
		for(int i = 2; i <= N; i++) {
			long val = Math.max(v[i], getMax(1, 1, N, Math.max(1, i - D), i - 1) + v[i]);
			update(1, 1, N, i, val);
		}
		
		System.out.println(getMax(1, 1, N, 1, N));
	}
	
	static void update(int node, int start, int end, int idx, long val) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			tree[node] = val;
			return;
		}
		
		int mid = (start + end) >> 1;
		
		if(idx <= mid) {
			update(node << 1, start, mid, idx, val);
		} else {
			update(node << 1 | 1, mid + 1, end, idx, val);
		}
		
		tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static long getMax(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return -1;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) >> 1;
		
		return Math.max(getMax(node << 1, start, mid, left, right), 
				getMax(node << 1 | 1, mid + 1, end, left, right));
	}
}