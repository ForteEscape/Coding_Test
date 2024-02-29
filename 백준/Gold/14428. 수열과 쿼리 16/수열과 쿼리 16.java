import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] A;
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N + 1];
		tree = new int[4 * N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(1, 1, N);
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			
			if(type == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, idx, value);
			} else if(type == 2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if(from > to) {
					int temp = from;
					from = to;
					to = temp;
				}
				
				System.out.println(query(1, 1, N, from, to));
			}
		}
	}
	
	public static void makeTree(int node, int start, int end) {
		if(start == end) {
			tree[node] = start;
			return;
		}
		
		int mid = (start + end) / 2;
		makeTree(node * 2, start, mid);
		makeTree(node * 2 + 1, mid + 1, end);
		
		if(A[tree[node * 2]] <= A[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}
	
	public static int query(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return -1;
		}
		
		if(start >= left && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		int leftMinIdx = query(node * 2, start, mid, left, right);
		int rightMinIdx = query(node * 2 + 1, mid + 1, end, left, right);
		
		if(leftMinIdx == -1) {
			return rightMinIdx;
		} else if(rightMinIdx == -1) {
			return leftMinIdx;
		} else {
			return A[leftMinIdx] <= A[rightMinIdx] ? leftMinIdx : rightMinIdx;
		}
	}
	
	public static void update(int node, int start, int end, int idx, int value) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			tree[node] = idx;
			A[idx] = value;
			return;
		}
		
		int mid = (start + end) / 2;
		
		update(node * 2, start, mid, idx, value);
		update(node * 2 + 1, mid + 1, end, idx, value);
		
		if(A[tree[node * 2]] <= A[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}
}