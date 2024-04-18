import java.util.*;
import java.io.*;

public class Main {

	static int V, E, K;
	static int[] parent;
	static int[] size;
	static int[] candy;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parent = new int[V + 1];
		size = new int[V + 1];
		candy = new int[V + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
			candy[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (find(from) != find(to)) {
				union(from, to);
			}
		}
		
		List<Integer> cost = new ArrayList<>();
		List<Integer> value = new ArrayList<>();
		int cnt = 0;

		for(int i = 1; i <= V; i++) {
			if(find(i) == i) {
				cnt++;
				cost.add(size[i]);
				value.add(candy[i]);
			}
		}
		
		dp = new int[cnt + 1][K];
		
		for(int i = 1; i <= cnt; i++) {
			for(int j = 1; j < K; j++) {
				if(j < cost.get(i - 1)) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost.get(i - 1)] + value.get(i - 1));
				}
			}
		}
		
		System.out.println(dp[cnt][K - 1]);
	}

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (size[pa] >= size[pb]) {
			parent[pb] = pa;
			size[pa] += size[pb];
			candy[pa] += candy[pb];
		} else {
			parent[pa] = pb;
			size[pb] += size[pa];
			candy[pb] += candy[pa];
		}
	}

	static int find(int x) {
		if (x == parent[x]) {
			return parent[x];
		}

		return parent[x] = find(parent[x]);
	}
}