import java.util.*;
import java.io.*;

public class Main {
	
	static int N, Q;
	static int[] arr;
	static int[][] countTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N + 1];
		countTree = new int[4 * N][2];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(1, 1, N);
		
		Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int option = Integer.parseInt(st.nextToken());
			
			if(option == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, idx, val);
			} else if(option == 2) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				
				System.out.println(countEven(1, 1, N, left, right));
			} else if(option == 3) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				
				System.out.println(countOdd(1, 1, N, left, right));
			}
		}
	}
	
	static void makeTree(int node, int start, int end) {
		if(start == end) {
			if(arr[start] % 2 == 0) {
				countTree[node][0]++;
			} else {
				countTree[node][1]++;
			}
			
			return;
		}
		
		int mid = (start + end) / 2;
		makeTree(node * 2, start, mid);
		makeTree(node * 2 + 1, mid + 1, end);
		
		countTree[node][0] = countTree[node * 2][0] + countTree[node * 2 + 1][0];
		countTree[node][1] = countTree[node * 2][1] + countTree[node * 2 + 1][1];
	}
	
	static int countEven(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return countTree[node][0];
		}
		
		int mid = (start + end) / 2;
		
		return countEven(node * 2, start, mid, left, right) + 
				countEven(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static int countOdd(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return countTree[node][1];
		}
		
		int mid = (start + end) / 2;
		
		return countOdd(node * 2, start, mid, left, right) + 
				countOdd(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static void update(int node, int start, int end, int idx, int val) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			if(arr[start] % 2 == 0 && val % 2 != 0) {
				countTree[node][0]--;
				countTree[node][1]++;
			} else if(arr[start] % 2 != 0 && val % 2 == 0) {
				countTree[node][1]--;
				countTree[node][0]++;
			}
			
			arr[start] = val;
			return;
		}
		
		int mid = (start + end) / 2;
		update(node * 2, start, mid, idx, val);
		update(node * 2 + 1, mid + 1, end, idx, val);
		
		countTree[node][0] = countTree[node * 2][0] + countTree[node * 2 + 1][0];
		countTree[node][1] = countTree[node * 2][1] + countTree[node * 2 + 1][1];
	}
}