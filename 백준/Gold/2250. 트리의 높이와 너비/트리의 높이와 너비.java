import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static List<Integer>[] tree;
	static int root;
	static Map<Integer, TreeSet<Integer>> map;
	static int idx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		tree = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int parent = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			tree[parent].add(left);
			tree[parent].add(right);
		}
		
		root = searchRoot();
		map = new TreeMap<>();
		idx = 1;
		
		int minWidth = Integer.MIN_VALUE;
		int minWidthLevel = -1;
		
		traversal(root, 1);
		
		for(int key : map.keySet()) {
			int left = map.get(key).first();
			int right = map.get(key).last();
			
			int width = right - left + 1;
			
			if(minWidth < width) {
				minWidth = width;
				minWidthLevel = key;
			}
		}
		
		System.out.println(minWidthLevel + " " + minWidth);
	}
	
	public static void traversal(int startNode, int depth) {
		if(tree[startNode].get(0) != -1) {
			traversal(tree[startNode].get(0), depth + 1);
		}
		
		if(!map.containsKey(depth)) {
			map.put(depth, new TreeSet<>());
		}
		map.get(depth).add(idx++);
		
		if(tree[startNode].get(1) != -1) {
			traversal(tree[startNode].get(1), depth + 1);
		}
	}
	
	public static int searchRoot() {
		int maxNode = Integer.MIN_VALUE;
		int rootNode = -1;
		
		for(int i = 1; i <= N; i++) {
			int nodeCnt = bfs(i);
			
			if(maxNode < nodeCnt) {
				maxNode = nodeCnt;
				rootNode = i;
			}
		}
		
		return rootNode;
	}
	
	public static int bfs(int startNode) {
		boolean[] visited = new boolean[N + 1];
		Deque<Integer> queue = new ArrayDeque<>();
		queue.addLast(startNode);
		
		int nodeCnt = 1;
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			
			for(int adjNode : tree[cur]) {
				if(adjNode != -1 && !visited[adjNode]) {
					visited[adjNode] = true;
					queue.addLast(adjNode);
					nodeCnt++;
				}
			}
		}
		
		return nodeCnt;
	}
}