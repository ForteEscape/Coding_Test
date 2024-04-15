import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static List<Integer>[] graph;
	static int[] childCnt;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		graph = new List[N];
		childCnt = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[to].add(from);
			graph[from].add(to);
			
			childCnt[from]++;
			childCnt[to]++;
		}

		Deque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			if(childCnt[i] == 1) {
				queue.addLast(i);
			}
		}

		while(queue.size() > 2) {
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				int cur = queue.pollFirst();
				
				for(int adjNode : graph[cur]) {
					if(!visited[adjNode]) {
						childCnt[adjNode]--;
						
						if(childCnt[adjNode] == 1) {
							queue.addLast(adjNode);
							visited[adjNode] = true;
						}
					}
				}
			}
		}

		List<Integer> result = new ArrayList<>(queue);
		
		for(int i = 0; i < N; i++) {
			if(childCnt[i] > 1) {
				result.add(i);
			}
		}

		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		for (int element : result) {
			sb.append(element).append(" ");
		}
		System.out.println(sb);
	}

}