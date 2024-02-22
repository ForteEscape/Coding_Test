import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		int idx;
		int time;
		Set<Integer> indegree;
		
		Node(int idx, int time) {
			this.idx = idx;
			this.time = time;
			this.indegree = new HashSet<>();
		}
	}
	
	static int[] indegree;
	static int[] dp;
	static List<Node>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		
		indegree = new int[V + 1];
		dp = new int[V + 1];
		graph = new List[V + 1];
		
		Deque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			int parentCnt = Integer.parseInt(st.nextToken());
			indegree[i] = parentCnt;
			
			Node node = new Node(i, time);
			
			for(int j = 0; j < parentCnt; j++) {
				int parent = Integer.parseInt(st.nextToken());
				
				node.indegree.add(parent);
				graph[parent].add(node);
			}
			
			if(indegree[i] == 0) {
				dp[i] = time;
				queue.addLast(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			
			for(Node adjNode : graph[cur]) {
				indegree[adjNode.idx]--;
				
				if(indegree[adjNode.idx] == 0) {
					queue.addLast(adjNode.idx);
					
					int completeTime = 0;
					for(int element : adjNode.indegree) {
						completeTime = Math.max(completeTime, dp[element]);
					}
					
					dp[adjNode.idx] = completeTime + adjNode.time;
				}
			}
		}
		
		int ans = -1;
		for(int i = 1; i <= V; i++) {
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
		
	}
	
}