import java.io.*;
import java.util.*;

public class Main {
	
	// L = 1 ~ 100_000;
	// SegmentTree NlogN
	static int[] height;
	static int[] tree;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		height = new int[N];
		tree = new int[4 * N + 1];
		
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		
		makeTree(1, 0, N - 1);
		
		System.out.println(getMaxArea(0, N - 1));
	}
	
	static void makeTree(int node, int left, int right) {
		if(left == right) {
			tree[node] = left;
			return;
		}
		
		int mid = (left + right) / 2;
		makeTree(node * 2, left, mid);
		makeTree(node * 2 + 1, mid + 1, right);
				
		if(height[tree[node * 2]] <= height[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}
	
	static int query(int node, int start, int end, int left, int right) {
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
			return height[leftMinIdx] <= height[rightMinIdx] ? leftMinIdx : rightMinIdx;
		}
	}
	
	static long getMaxArea(int left, int right) {
		int minIdx = query(1, 0, N - 1, left, right);
		long area = (right - left + 1) * (long)height[minIdx];
		
		if(minIdx + 1 <= right) {
			long tmp = getMaxArea(minIdx + 1, right);
			area = Math.max(area, tmp);
		}
		
		if(minIdx - 1 >= left) {
			long tmp = getMaxArea(left, minIdx - 1);
			area = Math.max(area, tmp);
		}
		
		return area;
	}
}