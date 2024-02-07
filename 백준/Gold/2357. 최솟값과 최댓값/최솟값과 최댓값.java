import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static long[] maxSegTree;
	static long[] minSegTree;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		maxSegTree = new long[1 << (height + 1)];
		minSegTree = new long[1 << (height + 1)];
		
		int[] data = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		makeMaxTree(data, 1, 1, N);
		makeMinTree(data, 1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(start > end) {
				int temp = start;
				start = end;
				end = temp;
			}
			
			sb.append(getMin(start, end, 1, 1, N)).append(" ").append(getMax(start, end, 1, 1, N)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void makeMaxTree(int[] data, int node, int left, int right) {
		if(left == right) {
			maxSegTree[node] = data[left];
			return;
		}
		
		int mid = (left + right) / 2;
		makeMaxTree(data, node * 2, left, mid);
		makeMaxTree(data, node * 2 + 1, mid + 1, right);
		
		maxSegTree[node] = Math.max(maxSegTree[node * 2], maxSegTree[node * 2 + 1]);
	}
	
	public static void makeMinTree(int[] data, int node, int left, int right) {
		if(left == right) {
			minSegTree[node] = data[left];
			return;
		}
		
		int mid = (left + right) / 2;
		makeMinTree(data, node * 2, left, mid);
		makeMinTree(data, node * 2 + 1, mid + 1, right);
		
		minSegTree[node] = Math.min(minSegTree[node * 2], minSegTree[node * 2 + 1]);
	}
	
	public static long getMin(int start, int end, int node, int left, int right) {
		if(start <= left && right <= end) {
			return minSegTree[node];
		}
		
		if(start > right || end < left) {
			return Long.MAX_VALUE;
		}
		
		int mid = (left + right) / 2;
		return Math.min(getMin(start, end, node * 2, left, mid), getMin(start, end, node * 2 + 1, mid + 1, right));
	}
	
	public static long getMax(int start, int end, int node, int left, int right) {
		if(start <= left && right <= end) {
			return maxSegTree[node];
		}
		
		if(start > right || end < left) {
			return Long.MIN_VALUE;
		}
		
		int mid = (left + right) / 2;
		return Math.max(getMax(start, end, node * 2, left, mid), getMax(start, end, node * 2 + 1, mid + 1, right));
	}
}