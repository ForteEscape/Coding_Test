import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Edge {
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N, M;
	static int[][] board;
	static List<Node>[] list;
	static int[] parent;
	static int[] size;
	static boolean[][] visited;
	static int idx;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		list = new List[7];
		visited = new boolean[N][M];
		idx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1 && !visited[i][j]) {
					list[idx] = new ArrayList<>();
					bfs(i, j);
					idx++;
				}
			}
		}

		pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

		for (int i = 1; i < idx; i++) {
			for (int j = 0; j < list[i].size(); j++) {
				Node current = list[i].get(j);

				for (int k = 0; k < 4; k++) {
					findBridge(current.y, current.x, i, k, -1);
				}
			}
		}

		parent = new int[idx + 1];
		size = new int[idx + 1];
		for (int i = 1; i < idx; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		int ans = getMST();
		System.out.println(ans);
	}

	static int getMST() {
		int cnt = 0;
		int ans = 0;

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				cnt++;
				ans += edge.weight;
			}

			if (cnt == idx - 1) {
				break;
			}
		}

		return cnt < idx - 2 ? -1 : ans;
	}

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(size[pa] >= size[pb]) {
			parent[pb] = pa;
			size[pa] += size[pb];
		} else {
			parent[pa] = pb;
			size[pb] += size[pa];
		}
	}

	static int find(int x) {
		if(x == parent[x]) {
			return parent[x];
		}
		
		return parent[x] = find(parent[x]);
	}

	static void findBridge(int y, int x, int idx, int dir, int len) {
		if (board[y][x] != 0 && board[y][x] != idx) {
			if (len >= 2) {
				pq.offer(new Edge(idx, board[y][x], len));
			}

			return;
		}

		int ny = y + dy[dir];
		int nx = x + dx[dir];

		if (isUnreachable(ny, nx)) {
			return;
		}

		if (board[ny][nx] == idx) {
			return;
		}

		findBridge(ny, nx, idx, dir, len + 1);
	}

	static void bfs(int y, int x) {
		visited[y][x] = true;

		Deque<Node> queue = new ArrayDeque<>();
		queue.addLast(new Node(y, x));
		list[idx].add(new Node(y, x));
		board[y][x] = idx;

		while (!queue.isEmpty()) {
			Node cur = queue.pollFirst();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (isUnreachable(ny, nx)) {
					continue;
				}
				
				if(visited[ny][nx] || board[ny][nx] == 0) {
					continue;
				}

				visited[ny][nx] = true;
				board[ny][nx] = idx;
				list[idx].add(new Node(ny, nx));
				queue.addLast(new Node(ny, nx));
			}
		}
	}

	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}