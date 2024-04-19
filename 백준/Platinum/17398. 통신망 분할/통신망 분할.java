import java.util.*;
import java.io.*;

public class Main {

	static int V, E, Q;
	static int[] parent;
	static int[] size;
	static int[][] link;
	static int[] query;
	static long ans;
	static Set<Integer> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		parent = new int[V + 1];
		size = new int[V + 1];
		link = new int[E + 1][2];
		query = new int[Q];
		set = new HashSet<>();
		ans = 0;

		for (int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			link[i] = new int[] { start, end };
		}

		for (int i = 0; i < Q; i++) {
			query[i] = Integer.parseInt(br.readLine());
			set.add(query[i]);
		}

		for (int i = 1; i <= E; i++) {
			if (!set.contains(i)) {
				if (find(link[i][0]) != find(link[i][1])) {
					union(link[i][0], link[i][1]);
				}
			}
		}

		for (int i = Q - 1; i >= 0; i--) {
			if (find(link[query[i]][0]) != find(link[query[i]][1])) {
				ans += size[find(link[query[i]][0])] * size[find(link[query[i]][1])];
				union(link[query[i]][0], link[query[i]][1]);
			}
		}

		System.out.println(ans);
	}

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (size[pa] >= size[pb]) {
			parent[pb] = pa;
			size[pa] += size[pb];
		} else {
			parent[pa] = pb;
			size[pb] += size[pa];
		}
	}

	static int find(int x) {
		if (x == parent[x]) {
			return parent[x];
		}

		return parent[x] = find(parent[x]);
	}

}