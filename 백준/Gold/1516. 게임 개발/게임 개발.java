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
		int time;
		List<Integer> parentList;
		
		Node(int idx, int time, List<Integer> parentList) {
			this.idx = idx;
			this.time = time;
			this.parentList = parentList;
		}
	}
	
	static int[] indegree;
	static List[] graph;
	static int[] buildTime;
	static Node[] nodes;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		indegree = new int[N + 1];
		graph = new List[N + 1];
		buildTime = new int[N + 1];
		nodes = new Node[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			List<Integer> list = new ArrayList<>();
			while(true) {
				int parent = Integer.parseInt(st.nextToken());
				
				if(parent == -1) {
					break;
				}
				
				list.add(parent);
				indegree[i]++;
				graph[parent].add(i);
			}
			
			nodes[i] = new Node(i, time, list);
		}
		
		Deque<Node> queue = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				queue.addLast(nodes[i]);
			}
		}
		
		while(!queue.isEmpty()) {
			Node cur = queue.pollFirst();
			
			int prevTime = 0;
			for(int i = 0; i < cur.parentList.size(); i++) {
				int parent = cur.parentList.get(i);
				prevTime = Math.max(prevTime, buildTime[parent]);
			}
			
			buildTime[cur.idx] = prevTime + cur.time;
			
			for(int i = 0; i < graph[cur.idx].size(); i++) {
				int adjNode = (int) graph[cur.idx].get(i);
				
				indegree[adjNode]--;
				if(indegree[adjNode] == 0) {
					queue.addLast(nodes[adjNode]);
				}
			}
		}
		
		for(int i = 1; i<=N; i++) {
			System.out.println(buildTime[i]);
		}
	}
}