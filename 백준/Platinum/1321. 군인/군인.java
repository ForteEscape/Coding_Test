import java.util.*;
import java.io.*;

public class Main {
	
	static int[] arr;
	static long[] tree;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		tree = new long[4 * N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(1, 1, N);
		
		Q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int opt = Integer.parseInt(st.nextToken());
			if(opt == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, idx, val);
			} else {
				long k = Long.parseLong(st.nextToken());
				sb.append(getKth(1, 1, N, k)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void makeTree(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return;
		}
		
		int mid = (start + end) >> 1;
		
		makeTree((node << 1), start, mid);
		makeTree((node << 1) + 1, mid + 1, end);
		
		tree[node] = tree[node << 1] + tree[(node << 1) + 1];
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
			update((node << 1), start, mid, idx, val);
		} else {
			update((node << 1) + 1, mid + 1, end, idx, val);
		}
		
		tree[node] = tree[(node << 1)] + tree[(node << 1) + 1];
	}
	
	static long getKth(int node, int start, int end, long k) {
		if(start == end) {
			return start;
		}
		
		int mid = (start + end) >> 1;
		
		if(k <= tree[node << 1]) {
			return getKth((node << 1), start, mid, k);
		} else {
			return getKth((node << 1) + 1, mid + 1, end, k - tree[node << 1]);
		}
	}
}