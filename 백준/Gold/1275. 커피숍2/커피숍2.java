import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		int height = (int) (Math.ceil(Math.log(N) / Math.log(2)));
		tree = new long[1 << (height + 1)];

		int[] data = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		makeTree(data, 1, 1, N);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(x > y) {
				int temp = x;
				x = y;
				y = temp;
			}

			sb.append(sum(x, y, 1, 1, N)).append("\n");
			update(a, b, 1, 1, N);
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

		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	public static long sum(int start, int end, int node, int left, int right) {
		// 완전히 포함되는 경우
		if (start <= left && right <= end) {
			return tree[node];
		}
		// 완전히 벗어나는 경우
		if (start > right || end < left) {
			return 0L;
		}

		int mid = (left + right) / 2;
		// 그렇지 않는 경우
		return sum(start, end, node * 2, left, mid) + sum(start, end, node * 2 + 1, mid + 1, right);
	}

	public static void update(int idx, int value, int node, int left, int right) {
		if (left == right) {
			tree[node] = value;
			return;
		}

		int mid = (left + right) / 2;
		// 인덱스가 다음 턴에 속해 있는 경우
		if (left <= idx && idx <= mid) {
			update(idx, value, node * 2, left, mid);
		} else {
			update(idx, value, node * 2 + 1, mid + 1, right);
		}

		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
}