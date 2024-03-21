import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int[] tree;
	static int[] arr;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		tree = new int[4 * N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(1, 1, N);
		
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int option = Integer.parseInt(st.nextToken());
			
			if(option == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, idx, val);
			} else {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				int maxIdx = query(1, 1, N, l, r);
				
				int tmp1 = -1;
				int tmp2 = -1;
				if(maxIdx - 1 >= l) {
					tmp1 = query(1, 1, N, l, maxIdx - 1);
				}
				
				if(maxIdx + 1 <= r) {
					tmp2 = query(1, 1, N, maxIdx + 1, r);
				}
				
				int maxIdx2 = -1;
				if(tmp1 == -1) {
					maxIdx2 = tmp2;
				} else if(tmp2 == -1) {
					maxIdx2 = tmp1;
				} else {
					maxIdx2 = arr[tmp1] >= arr[tmp2] ? tmp1 : tmp2;
				}
				
				long ans = (long)arr[maxIdx] + arr[maxIdx2];
				
				sb.append(ans).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	static void makeTree(int node, int start, int end) {
		if(start == end) {
			tree[node] = start;
			return;
		}
		
		int mid = (start + end) >> 1;
		
		makeTree(node << 1, start, mid);
		makeTree(node << 1 | 1, mid + 1, end);
		
		tree[node] = arr[tree[node << 1]] >= arr[tree[node << 1 | 1]] ? tree[node << 1] : tree[node << 1 | 1];
	}
	
	static int query(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return -1;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) >> 1;
		
		int l = query(node << 1, start, mid, left, right);
		int r = query(node << 1 | 1, mid + 1, end, left, right);
		
		if(l == -1) {
			return r;
		} else if(r == -1) {
			return l;
		}
		
		return arr[l] >= arr[r] ? l : r;
	}
	
	static void update(int node, int start, int end, int idx, int val) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			arr[idx] = val;
			return;
		}
		
		int mid = (start + end) >> 1;
		
		update(node << 1, start, mid, idx, val);
		update(node << 1 | 1, mid + 1, end, idx, val);
		
		tree[node] = arr[tree[node << 1]] >= arr[tree[node << 1 | 1]] ? tree[node << 1] : tree[node << 1 | 1];
	}
}