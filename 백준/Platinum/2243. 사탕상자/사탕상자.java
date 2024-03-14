import java.util.*;
import java.io.*;

public class Main {
	
	static int[] tree;
	static final int MAX = 1_000_001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		
		tree = new int[4 * MAX];
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int opt = Integer.parseInt(st.nextToken());
			if(opt == 1) {
				int k = Integer.parseInt(st.nextToken());
				int val = getKth(1, 1, MAX, k);
				update(1, 1, MAX, val, -1);
				sb.append(val).append("\n");
			} else if(opt == 2) {
				int idx = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				update(1, 1, MAX, idx, val);
			}
		}
		
		System.out.println(sb);
	}
	
	static void update(int node, int start, int end, int idx, int val) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			tree[node] += val;
			return;
		}
		
		int mid = (start + end) / 2;
		
		if(idx <= mid) {
			update(node * 2, start, mid, idx, val);
		} else {
			update(node * 2 + 1, mid + 1, end, idx, val);
		}
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	static int getKth(int node, int start, int end, int idx) {
		if(start == end) {
			return start;
		}
		
		int mid = (start + end) / 2;
		
		if(idx <= tree[node * 2]) {
			return getKth(node * 2, start, mid, idx);
		} else {
			return getKth(node * 2 + 1, mid + 1, end, idx - tree[node * 2]);
		}
	}
}