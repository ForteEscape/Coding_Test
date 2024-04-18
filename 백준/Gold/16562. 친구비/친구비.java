import java.util.*;
import java.io.*;

public class Main {
	
	static int V, E, K;
	static int[] parent;
	static int[] size;
	static int[] price;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		size = new int[V + 1];
		price = new int[V + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
			size[i] = 1;
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(find(from) != find(to)) {
				union(from, to);
			}
		}
		
		int ans = 0;
		for(int i = 1; i <= V; i++) {
			if(find(i) == i) {
				ans += price[i];
			}
		}
		
		System.out.println(ans <= K ? ans : "Oh no");
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(size[pa] >= size[pb]) {
			parent[pb] = pa;
			size[pa] += pb;
			
			price[pa] = Math.min(price[pa], price[pb]);
		} else {
			parent[pa] = pb;
			size[pb] += pa;
			
			price[pb] = Math.min(price[pb], price[pa]);
		}
	}
	
	static int find(int x) {
		if(x == parent[x]) {
			return parent[x];
		}
		
		return parent[x] = find(parent[x]);
	}
}