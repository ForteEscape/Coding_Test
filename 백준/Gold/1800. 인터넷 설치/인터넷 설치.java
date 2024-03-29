import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Edge {
		int to;
		int weight;

		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", weight=" + weight + "]";
		}
	}

	static int dist[];
	static int V, E, K;
	static List<Edge>[] graph;
	static Map<Integer, Edge> edgeMap;
	static final int INF = 1 << 25;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dist = new int[V + 1];
		graph = new List[V + 1];
		edgeMap = new HashMap<>();

		int start = 0;
		int end = -1;

		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));

			end = Math.max(end, weight);
		}

		while (start < end) {
			int mid = (start + end) >> 1;

			if (dijkstra(mid)) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(dist[V] == INF ? -1 : end);
	}

	static boolean dijkstra(int midWeight) {
		for (int i = 1; i <= V; i++) {
			dist[i] = INF;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
		pq.offer(new Edge(1, 0));
		dist[1] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (dist[cur.to] < cur.weight) {
				continue;
			}

			for (Edge next : graph[cur.to]) {
				int nextWeight = cur.weight + (next.weight > midWeight ? 1 : 0);

				if (dist[next.to] > nextWeight) {
					dist[next.to] = nextWeight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}

		return dist[V] <= K;
	}
}