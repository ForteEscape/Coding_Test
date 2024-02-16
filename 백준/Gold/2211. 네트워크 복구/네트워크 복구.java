import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static class Line {
		int from;
		int to;
		
		Line(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + from;
			result = prime * result + to;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Line other = (Line) obj;
			if (from != other.from)
				return false;
			if (to != other.to)
				return false;
			return true;
		}
	}
	
	static int[] dist;
	static int[] prevNode;
	static List<Edge>[] graph;
	static int V, E;
	static final int INF = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        dist = new int[V + 1];
        prevNode = new int[V + 1];
        graph = new List[V + 1];
        
        for(int i = 1; i <= V; i++) {
        	dist[i] = INF;
        	prevNode[i] = -1;
        	graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());
        	
        	graph[from].add(new Edge(to, weight));
        	graph[to].add(new Edge(from, weight));
        }
        
        dijkstra(1);
        
        Set<Line> ans = new HashSet<>();
        
        for(int i = 2; i <= V; i++) {
        	int idx = i;
        	while(prevNode[idx] != -1) {
        		ans.add(new Line(idx, prevNode[idx]));
        		idx = prevNode[idx];
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for(Line element : ans) {
        	sb.append(element.from).append(" ").append(element.to).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void dijkstra(int startNode) {
    	PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
    	pq.offer(new Edge(startNode, 0));
    	dist[startNode] = 0;
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		
    		if(dist[cur.to] > cur.weight) {
    			continue;
    		}
    		
    		if(dist[cur.to] < cur.weight) {
    			continue;
    		}
    		
    		for(Edge next : graph[cur.to]) {
    			if(dist[next.to] > cur.weight + next.weight) {
    				dist[next.to] = cur.weight + next.weight;
    				prevNode[next.to] = cur.to;
    				pq.offer(new Edge(next.to, dist[next.to]));
    			}
    		}
    	}
    }
}