import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge {
		int to;
		int count;
		
		Edge(int to, int count) {
			this.to = to;
			this.count = count;
		}
	}
	
	static int[][] dp; // K번 부품에 필요한 기본 부품의 개수
	static int[] indegree; // indegree가 0인 부품은 기본 부품이다.
	static List[] graph;
	static List<Integer> list;
	static int N, M;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1][N + 1];
		
		indegree = new int[N + 1];
		graph = new List[N + 1];
		list = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cur = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			
			indegree[cur]++;
			graph[parent].add(new Edge(cur, count));
		}
		
		Deque<Edge> queue = new ArrayDeque<>();
		int cnt = 0;
		// 기본 부품 
		for(int i = 1; i<= N; i++) {
			if(indegree[i] == 0) {
				list.add(i);
				dp[i][i] = 1;
				queue.addLast(new Edge(i, 0));
			}
		}
		
		// bottom up으로 dp fill
		while(!queue.isEmpty()) {
			Edge cur = queue.pollFirst();
			
			for(int i = 0; i < graph[cur.to].size(); i++) {
				Edge next = (Edge) graph[cur.to].get(i);
				
				for(int basicPart : list) {
					dp[next.to][basicPart] += dp[cur.to][basicPart] * next.count;
				}
				
				indegree[next.to]--;
				if(indegree[next.to] == 0) {
					queue.addLast(next);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int element: list) {
			sb.append(element).append(" ").append(dp[N][element]).append("\n");
		}
		System.out.println(sb);
	}
}