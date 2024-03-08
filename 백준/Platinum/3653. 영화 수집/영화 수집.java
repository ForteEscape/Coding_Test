import java.util.*;
import java.io.*;

public class Main {
	
	static int[] arr;
	static long[] tree;
	static Map<Integer, Integer> map;
	static int T, N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N + M + 1];
			tree = new long[arr.length * 4];
			map = new HashMap<>();
			int endPoint = N + 1;
			
			for(int i = 1, j = N; i <= N; i++, j--) {
				arr[i] = 1;
				map.put(j, i);
			}
			
			makeTree(1, 1, arr.length - 1);
			
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) {
				int idx = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, 1, arr.length - 1, map.get(idx), arr.length) - 1).append(" ");
				update(1, 1, arr.length - 1, map.get(idx), 0);
				update(1, 1, arr.length - 1, endPoint, 1);
				
				map.put(idx, endPoint);
				endPoint++;
				
//				System.out.println(Arrays.toString(arr));
//				System.out.println(Arrays.toString(tree));
//				System.out.println("===");
			}
			
			System.out.println(sb);
		}
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
	
	static long query(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		
		return query(node * 2, start, mid, left, right) + 
				query(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static void update(int node, int start, int end, int idx, int val) {
		if(start > idx || idx > end) {
			return;
		}
		
		if(start == end) {
			tree[node] = val;
			arr[start] = val;
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
}