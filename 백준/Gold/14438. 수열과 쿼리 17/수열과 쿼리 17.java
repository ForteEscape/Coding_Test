import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static long[] tree;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		tree = new long[1 << (height + 1)];

		int[] data = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		makeTree(data, 1, 1, N);

		int Q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int query = Integer.parseInt(st.nextToken());
			
			if(query == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				update(idx, val, 1, 1, N);
			} else if(query == 2) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				sb.append(getMin(start, end, 1, 1, N)).append("\n");
			}
		}
		
		System.out.println(sb);
	}

	public static void makeTree(int[] data, int node, int left, int right) {
		if (left == right) {
			tree[node] = data[left];
			return;
		}

		int mid = (left + right) / 2;
		makeTree(data, node * 2, left, mid);
		makeTree(data, node * 2 + 1, mid + 1, right);

		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
	}

	public static long getMin(int start, int end, int node, int left, int right) {
		if(start <= left && right <= end) {
			return tree[node];
		}
		
		if(start > right || end < left) {
			return Long.MAX_VALUE;
		}
		
		int mid = (left + right) / 2;
		return Math.min(getMin(start, end, node * 2, left, mid), 
				getMin(start, end, node * 2 + 1, mid + 1, right));
	}

	public static void update(int idx, int val, int node, int left, int right) {
		if (left == right) {
			tree[node] = val;
			return;
		}

		int mid = (left + right) / 2;
		if (left <= idx && idx <= mid) {
			update(idx, val, node * 2, left, mid);
		} else {
			update(idx, val, node * 2 + 1, mid + 1, right);
		}

		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
	}
}