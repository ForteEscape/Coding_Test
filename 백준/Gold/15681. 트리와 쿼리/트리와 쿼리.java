import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static StringTokenizer st;
	static int N, R, Q;
	static List[] tree;
	static int[] size;
	static int[] depth;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		tree = new List[N + 1];
		size = new int[N + 1];
		depth = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			tree[u].add(v);
			tree[v].add(u);
		}
		
		depth[R] = 1;
		dfs(R);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			int queryNode = Integer.parseInt(br.readLine());
			sb.append(size[queryNode] + 1).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int root) {
		for(int i = 0; i < tree[root].size(); i++) {
			int adjNode = (Integer) tree[root].get(i);
			
			if(depth[adjNode] == 0) {
				depth[adjNode] = depth[root] + 1;
				dfs(adjNode);
				size[root] += size[adjNode] + 1;
			}
		}
	}
}