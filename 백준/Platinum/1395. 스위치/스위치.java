import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int[] tree;
	static boolean[] lazy;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		tree = new int[4 * N];
		lazy = new boolean[4 * N];
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int opt = Integer.parseInt(st.nextToken());
			
			if(opt == 0) {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, l, r);
			} else {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, 1, N, l, r)).append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static void updateLazy(int node, int start, int end) {
		if(lazy[node]) {
			tree[node] = (end - start + 1) - tree[node];
			
			if(start != end) {
				lazy[node << 1] ^= true;
				lazy[node << 1 | 1] ^= true;
			}
			lazy[node] = false;
		}
	}
	
	static void update(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);
		
		if(start > right || end < left) {
			return;
		}
		
		if(start >= left && end <= right) {
			tree[node] = (end - start + 1) - tree[node];
			
			if(start != end) {
				lazy[node << 1] ^= true;
				lazy[node << 1 | 1] ^= true;
			}
			return;
		}
		
		int mid = (start + end) >> 1;
		
		update(node << 1, start, mid, left, right);
		update(node << 1 | 1, mid + 1, end, left, right);
		
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
	
	static int query(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);
		
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