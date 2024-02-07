import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		tree = new int[1 << (height + 1)];
		
		int[] data = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(data, 1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i = M; i <= N - M + 1; i++) {
			sb.append(query(i - (M - 1), i + (M - 1), 1, 1, N)).append(" ");
		}
		System.out.println(sb);
	}
	
	public static void makeTree(int[] data, int node, int left, int right) {
		if(left == right) {
			tree[node] = data[left];
			return;
		}
		
		int mid = (left + right) / 2;
		makeTree(data, node * 2, left, mid);
		makeTree(data, node * 2 + 1, mid + 1, right);
		
		tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
	}
	
	public static int query(int start, int end, int node, int left, int right) {
		if(start <= left && right <= end) {
			return tree[node];
		}
		
		if(start > right || end < left) {
			return Integer.MIN_VALUE;
		}
		
		int mid = (left + right) / 2;
		return Math.max(query(start, end, node * 2, left, mid), query(start, end, node * 2 + 1, mid + 1, right));
	}
}