import java.util.*;
import java.io.*;

class Main {

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
		int to;
		long weight;

		Node(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "[to= " + this.to + ", weight=" + this.weight + "]";
		}
	}

	static int V, E;
	static Edge[] edges;
	static int[] parent;
	static int[] size;
	static List<Node>[] graph;
	static boolean[] visited;
	static int startNode;
	static long maxDist;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parent = new int[V];
		size = new int[V];
		edges = new Edge[E];
		graph = new List[V];

		for (int i = 0; i < V; i++) {
			parent[i] = i;
			size[i] = 1;
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(from, to, weight);
		}

		Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

		long ans = 0L;
		int maxWeight = -1;
		int maxWeightNode = -1;
		int cnt = 0;

		for (Edge edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				ans += edge.weight;
				cnt++;

				graph[edge.from].add(new Node(edge.to, edge.weight));
				graph[edge.to].add(new Node(edge.from, edge.weight));

				if (edge.weight > maxWeight) {
					maxWeight = edge.weight;
					maxWeightNode = edge.from;
				}
			}

			if (cnt == V - 1) {
				break;
			}
		}
		
		startNode = 0;
		maxDist = 0;
		bfs(0);
		
		maxDist = 0;
		bfs(startNode);
		
		System.out.println(ans);
		System.out.println(maxDist);
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

	static void bfs(int start) {
		visited = new boolean[V];
		visited[start] = true;
		
		Deque<Node> queue = new ArrayDeque<>();
		queue.addLast(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node cur = queue.pollFirst();
			
			if(maxDist < cur.weight) {
				maxDist = cur.weight;
				startNode = cur.to;
			}
			
			for(Node adjNode : graph[cur.to]) {
				if(!visited[adjNode.to]) {
					visited[adjNode.to] = true;
					queue.addLast(new Node(adjNode.to, cur.weight + adjNode.weight));
				}
			}
		}
	}
	
}