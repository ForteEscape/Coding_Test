import java.util.*;
import java.io.*;

public class Main {
	
	// 현재까지 들어온 숫자의 개수를 관리하는 SegTree
	// segTree의 리프 노드엔 해당 값에 몇 개의 숫자가 들어있는지를 나타낸다.
	static final int MAX = 2_000_002;
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		tree = new int[4 * MAX];
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int option = Integer.parseInt(st.nextToken());
			
			if(option == 1) {
				int x = Integer.parseInt(st.nextToken());
				update(1, 1, MAX, x, 1);
			} else if(option == 2) {
				int k = Integer.parseInt(st.nextToken());
				int val = getKth(1, 1, MAX, k);
				update(1, 1, MAX, val, -1);
				sb.append(val).append("\n");
			}
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
		
		int mid = (start + end) / 2;
		if(idx <= mid) {
			update(node * 2, start, mid, idx, val);
		} else {
			update(node * 2 + 1, mid + 1, end, idx, val);
		}
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	static int getKth(int node, int start, int end, int k) {
		if(start == end) {
			return start;
		}
		
		int mid = (start + end) / 2;
		
		if(k <= tree[node * 2]) {
			return getKth(node * 2, start, mid, k);
		} else {
			return getKth(node * 2 + 1, mid + 1, end, k - tree[node * 2]);
		}
	}
}