import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static long[] sumTree;
	static int[] minTree;
	static long[] arr;
	static long ans = Long.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		arr = new long[N + 1];
		sumTree = new long[4 * N];
		minTree = new int[4 * N];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makeSumTree(1, 1, N);
		makeMinTree(1, 1, N);
		
		System.out.println(solve(1, N));
	}
	
	static long solve(int start, int end) {
		if(start > end) {
			return 0;
		}
		
		long rangeSum = sum(1, 1, N, start, end);
		int minIdx = minIdx(1, 1, N, start, end);
		
		long area = rangeSum * arr[minIdx];
		
		if(start == end) {
			return area;
		}
		
		return Math.max(area, Math.max(solve(start, minIdx - 1), solve(minIdx + 1, end)));
	}
	
	static void makeSumTree(int node, int start, int end) {
		if(start == end) {
			sumTree[node] = arr[start];
			return;
		}
		
		int mid = (start + end) / 2;
		makeSumTree(node * 2, start, mid);
		makeSumTree(node * 2 + 1, mid + 1, end);
		
		sumTree[node] = sumTree[node * 2] + sumTree[node * 2 + 1];
	}
	
	static void makeMinTree(int node, int start, int end) {
		if(start == end) {
			minTree[node] = start;
			return;
		}
		
		int mid = (start + end) / 2;
		makeMinTree(node * 2, start, mid);
		makeMinTree(node * 2 + 1, mid + 1, end);
		
		if(arr[minTree[node * 2]] <= arr[minTree[node * 2 + 1]]) {
			minTree[node] = minTree[node * 2];
		} else {
			minTree[node] = minTree[node * 2 + 1];
		}
	}
	
	static long sum(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return 0;
		}
		
		if(left <= start && end <= right) {
			return sumTree[node];
		}
		
		int mid = (start + end) / 2;
		
		return sum(node * 2, start, mid, left, right) + 
				sum(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static int minIdx(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return -1;
		}
		
		if(left <= start && end <= right) {
			return minTree[node];
		}
		
		int mid = (start + end) / 2;
		int leftMinIdx = minIdx(node * 2, start, mid, left, right);
		int rightMinIdx = minIdx(node * 2 + 1, mid + 1, end, left, right);
		
		if(leftMinIdx == -1) {
			return rightMinIdx;
		} else if(rightMinIdx == -1) {
			return leftMinIdx;
		}
		
		return arr[leftMinIdx] <= arr[rightMinIdx] ? leftMinIdx : rightMinIdx;
	}
}