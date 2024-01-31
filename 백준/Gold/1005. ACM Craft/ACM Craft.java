import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int idx;
		int buildTime;
		int indegree;
		List<Node> linked;
		List<Node> branches;
		
		Node(int idx, int buildTime){
			this.idx = idx;
			this.buildTime = buildTime;
			this.indegree = 0;
			linked = new ArrayList<>();
			branches = new ArrayList<>();
		}
	}
	
	static int V;
	static int E;
	static StringTokenizer st;
	static int[] dp;
	static Node[] nodes;
	 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			nodes = new Node[V + 1];
			dp = new int[V + 1];
			
			int[] time = new int[V + 1];
			
			for(int i = 1; i <= V; i++) {
				time[i] = Integer.parseInt(st.nextToken());
				nodes[i] = new Node(i, time[i]);
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				nodes[to].indegree++;
				nodes[to].linked.add(nodes[from]);
				nodes[from].branches.add(nodes[to]);
			}
			
			Deque<Node> queue = new ArrayDeque<>();
			for(int i = 1; i <= V; i++) {
				if(nodes[i].indegree == 0) {
					queue.addLast(nodes[i]);
					dp[i] = time[i];
				}
			}
			
			while(!queue.isEmpty()) {
				Node cur = queue.pollFirst();
				
				for(int i = 0; i < cur.linked.size(); i++) {
					Node adjNode = cur.linked.get(i);
					
					dp[cur.idx] = Math.max(dp[cur.idx], dp[adjNode.idx] + time[cur.idx]);
				}
				
				for(int i = 0; i < cur.branches.size(); i++) {
					Node adjNode = cur.branches.get(i);
					
					adjNode.indegree--;
					if(adjNode.indegree == 0) {
						queue.addLast(adjNode);
					}
				}
			}
			
			int key = Integer.parseInt(br.readLine());
			System.out.println(dp[key]);
		}
	}

}