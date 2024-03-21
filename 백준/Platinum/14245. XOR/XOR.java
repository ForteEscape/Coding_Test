import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int[] arr;
	static long[] tree;
	static long[] lazy;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		tree = new long[4 * N];
		lazy = new long[4 * N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int opt = Integer.parseInt(st.nextToken());
			
			if(opt == 1) {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				l++;r++;
				
				update(1, 1, N, l, r, c);
			} else {
				int idx = Integer.parseInt(st.nextToken());
				idx++;
				
				sb.append(query(1, 1, N, idx, idx)).append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static void makeTree(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return;
		}
		
		int mid = (start + end) >> 1;
		
		makeTree(node << 1, start, mid);
		makeTree(node << 1 | 1, mid + 1, end);
		
		tree[node] = tree[node << 1] ^ tree[node << 1 | 1];
	}
	
	static void lazyUpdate(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] ^= (end - start + 1) * lazy[node];
			
			if(start != end) {
				lazy[node << 1] ^= lazy[node];
				lazy[node << 1 | 1] ^= lazy[node];
			}
			
			lazy[node] = 0;
		}
	}
	
	static void update(int node, int start, int end, int left, int right, int val) {
		lazyUpdate(node, start, end);
		
		if(start > right || end < left) {
			return;
		}
		
		if(start >= left && end <= right) {
			tree[node] ^= (end - start + 1) * val;
			
			if(start != end) {
				lazy[node << 1] ^= val;
				lazy[node << 1 | 1] ^= val;
			}
			
			return;
		}
		
		int mid = (start + end) >> 1;
		
		update(node << 1, start, mid, left, right, val);
		update(node << 1 | 1, mid + 1, end, left, right, val);
		
		tree[node] = tree[node << 1] ^ tree[node << 1 | 1];
	}
	
	static long query(int node, int start, int end, int left, int right) {
		lazyUpdate(node, start, end);
		
		if(right < start || left > end) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) >> 1;
		
		return query(node << 1, start, mid, left, right) ^ 
				query(node << 1 | 1, mid + 1, end, left, right);
	}
}