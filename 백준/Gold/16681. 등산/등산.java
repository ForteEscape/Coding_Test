import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge {
		int to;
		long weight;

		Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static int V, E, D, H;
	static int[] height;
	static final long INF = Long.MAX_VALUE;
	static List[] graph;
	static long ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		long[] dist = new long[V + 1];
		long[] dist2 = new long[V + 1];
		height = new int[V + 1];
		graph = new List[V + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= V; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			dist[i] = INF;
			graph[i] = new ArrayList<>();
			dist2[i] = INF;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}

		dijkstra(1, dist);

//		목적지에서 낮아지는 방향으로 V까지 가야한다면 V에서 시작하여 목적지까지 높아지는 방향으로 가는 것과 동치이다.
//		따라서 1에서 출발한 도착점과 2에서 출발한 도착점이 모두 도달 불가능하지 않아야 한다.
		dijkstra(V, dist2);

		ans = Long.MIN_VALUE;
		for (int i = 2; i <= V - 1; i++) {
			if (dist[i] != INF && dist2[i] != INF) {
				ans = Math.max(ans, (height[i] * H) - (dist[i] + dist2[i]) * D);
			}
		}

		if (ans == Long.MIN_VALUE) {
			System.out.println("Impossible");
		} else {
			System.out.println(ans);
		}
	}

	public static void dijkstra(int startNode, long[] dist) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> (int) (e1.weight - e2.weight));
		dist[startNode] = 0;
		pq.offer(new Edge(startNode, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (dist[cur.to] > cur.weight) {
				continue;
			}

			if (dist[cur.to] < cur.weight) {
				continue;
			}

			for (int i = 0; i < graph[cur.to].size(); i++) {
				Edge next = (Edge) graph[cur.to].get(i);

				if (height[cur.to] < height[next.to] && dist[next.to] > cur.weight + next.weight) {
					dist[next.to] = cur.weight + next.weight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}