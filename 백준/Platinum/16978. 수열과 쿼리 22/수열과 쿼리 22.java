import java.util.*;
import java.io.*;

public class Main {
	static int N, Q;
	static int[] arr;
	static long[] tree;
	static List<int[]> query2;
	static List<int[]> query1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];
		tree = new long[4 * N];
		query2 = new ArrayList<>();
		query1 = new ArrayList<>();

		query1.add(new int[2]);

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		makeTree(1, 1, N);

		Q = Integer.parseInt(br.readLine());

		int queryCnt = 0;

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int type = Integer.parseInt(st.nextToken());
			if (type == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());

				query1.add(new int[] {idx, val});
			} else {
				int changeQueryIdx = Integer.parseInt(st.nextToken());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());

				query2.add(new int[] {queryCnt, changeQueryIdx, left, right});
				queryCnt++;
			}
		}

		Collections.sort(query2, Comparator.comparingInt(o -> o[1]));
		
		long[] result = new long[queryCnt];
//		System.out.println(query.toString());

		int index = 0;
		for (int i = 0; i < query1.size(); i++) {
			if (i > 0) {
				int idx = query1.get(i)[0];
				int val = query1.get(i)[1];

				update(1, 1, N, idx, val - arr[idx]);
				arr[idx] = val;
			}

			while (index < query2.size() && query2.get(index)[1] == i) {
				int left = query2.get(index)[2];
				int right = query2.get(index)[3];
				int idx = query2.get(index)[0];

				result[idx] = getSum(1, 1, N, left, right);
				index++;
			}
		}

		for (long res : result) {
			System.out.println(res);
		}
	}

	static void makeTree(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}

		int mid = (start + end) / 2;

		makeTree(node * 2, start, mid);
		makeTree(node * 2 + 1, mid + 1, end);

		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long getSum(int node, int start, int end, int left, int right) {
		if (start > right || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;

		return getSum(node * 2, start, mid, left, right) + getSum(node * 2 + 1, mid + 1, end, left, right);
	}

	static void update(int node, int start, int end, int idx, int diff) {
		int mid = (start + end) / 2;
		tree[node] += diff;

		if (start < end) {
			if (idx <= mid) {
				update(node * 2, start, mid, idx, diff);
			} else {
				update(node * 2 + 1, mid + 1, end, idx, diff);
			}
		}
	}
}