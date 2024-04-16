import java.util.*;
import java.io.*;

public class Main {

	static Set<Integer>[] graph;
	static int V, E;
	static int[] indegree;
	static List<Integer> candidateNode;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new Set[V + 1];
		indegree = new int[V + 1];
		candidateNode = new ArrayList<>();
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new HashSet<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
			
			indegree[from]++;
			indegree[to]++;
		}
		
		for(int i = 1; i <= V; i++) {
			if(indegree[i] >= 2) {
				candidateNode.add(i);
			}
		}
		
		if(candidateNode.size() < 3) {
			System.out.println(-1);
			return;
		}
		
		int ans = Integer.MAX_VALUE;
		int size = candidateNode.size();
		
		for(int i = 0; i < size - 2; i++) {
			int node1 = candidateNode.get(i);
			for(int j = i + 1; j < size - 1; j++) {
				int node2 = candidateNode.get(j);
				
				if(!graph[node1].contains(node2)) { // 친구 관계가 아니라면 바로 스킵
					continue;
				}
				
				for(int k = j + 1; k < size; k++) {
					int node3 = candidateNode.get(k);
					if(!graph[node1].contains(node3) || !graph[node2].contains(node3)) {
						continue;
					}
					
					int res = (graph[node1].size() + graph[node2].size() + graph[node3].size()) - 6;
					
					ans = Math.min(ans, res);
				}
			}
		}
		
		System.out.println(ans);
	}
	
}