import java.io.*;
import java.util.*;

public class Main {
	
	static List<Integer>[] graph;
	static int[] population;
	static int sum;
	static int N;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		population = new int[N + 1];
		graph = new List[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		sum = 0;
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			sum += population[i];
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				int to = Integer.parseInt(st.nextToken());
				
				graph[i].add(to);
			}
		}
		
		ans = Integer.MAX_VALUE;
		for(int i = 1; i <= N / 2; i++) {
			//System.out.println(i);
			comb(1, i, new HashSet<>());
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	public static void comb(int num, int r, Set<Integer> set) {
		if(r == 0) {
			if(set.size() == 0) return;
			
			int result = sum;
			int start = -1;
			int otherStart = -1;
			Set<Integer> otherSet = new HashSet<>();
			
			for(int i = 1; i <= N; i++) {
				if(set.contains(i)) {
					start = i;
					result -= population[i];
				} else {
					otherSet.add(i);
					otherStart = i;
				}
			}
			
			if(bfs(start, set) && bfs(otherStart, otherSet)) {
//				System.out.println(set.toString());
//				System.out.println(otherSet.toString());
//				System.out.println(result + " " + sum);
				ans = Math.min(ans, Math.abs((sum - result) - result));
			}
			
			return;
		}
		
		if(num > N) {
			return;
		}
		
		//System.out.println(r);
		
		set.add(num);
		comb(num + 1, r - 1, set);
		set.remove(num);
		comb(num + 1, r, set);
	}
	
	public static boolean bfs(int startNode, Set<Integer> set) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.addLast(startNode);
		
		boolean[] visited = new boolean[N + 1];
		visited[startNode] = true;
		
		Set<Integer> reachableSet = new HashSet<>();
		reachableSet.add(startNode);
		
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			
			for(int adjNode : graph[cur]) {
				if(!visited[adjNode] && set.contains(adjNode)) {
					visited[adjNode] = true;
					queue.addLast(adjNode);
					reachableSet.add(adjNode);
				}
			}
		}
		if(set.size() == reachableSet.size()) {
//			System.out.println("start : " + startNode);
//			System.out.println("set : " + set.toString());
//			System.out.println("reach : " + reachableSet.toString());
			return true;
		}
		return false;
	}
}