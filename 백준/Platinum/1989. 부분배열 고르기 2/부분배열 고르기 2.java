import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] arr;
	static long[] tree;
	static int[] minTree;
	static long ans;
	static int ansStart, ansEnd;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree = new long[4 * N];
		minTree = new int[4 * N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(1, 1, N);
		makeMinTree(1, 1, N);
		
		ans = Long.MIN_VALUE;
		ansStart = -1;
		ansEnd = -1;
		
		solve(1, N);
		
		System.out.println(ans);
		System.out.println(ansStart + " " + ansEnd);
	}
	
	static void solve(int start, int end) {
		if(start > end) return;
		
		int minIdx = getMinIdx(1, 1, N, start, end);
		long sum = getSum(1, 1, N, start, end);
		
		long result = sum * arr[minIdx];
		
		if(result > ans) {
			ans = result;
			ansStart = start;
			ansEnd = end;
		}
		
		solve(start, minIdx - 1);
		solve(minIdx + 1, end);
	}
	
	static void makeTree(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return;
		}
		
		int mid = (start + end) / 2;
		
		makeTree(node * 2, start, mid);
		makeTree(node * 2 + 1, mid + 1, end);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	static void makeMinTree(int node, int start, int end) {
		if(start == end) {
			minTree[node] = start;
			return;
		}
		
		int mid = (start + end) / 2;
		
		makeMinTree(node * 2, start, mid);
		makeMinTree(node * 2 + 1, mid + 1, end);
		
		minTree[node] = arr[minTree[node * 2]] <= arr[minTree[node * 2 + 1]] ? minTree[node * 2] : minTree[node * 2 + 1];
	}
	
	static long getSum(int node, int start, int end, int left, int right) {
		if(end < left || start > right) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		
		return getSum(node * 2, start, mid, left, right) + 
				getSum(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static int getMinIdx(int node, int start, int end, int left, int right) {
		if(end < left || start > right) {
			return -1;
		}
		
		if(start >= left && end <= right) {
			return minTree[node];
		}
		
		int mid = (start + end) / 2;
		
		int leftMinIdx = getMinIdx(node * 2, start, mid, left, right);
		int rightMinIdx = getMinIdx(node * 2 + 1, mid + 1, end, left, right);
		
		if(leftMinIdx == -1) {
			return rightMinIdx;
		} else if(rightMinIdx == -1) {
			return leftMinIdx;
		}
		
		return arr[leftMinIdx] <= arr[rightMinIdx] ? leftMinIdx : rightMinIdx;
	}
}