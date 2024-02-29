import java.io.*;
import java.util.*;

public class Main {
	
	static int[] parent;
	static int[] size;
	static int N, M;
	static List<Integer>[] partyList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		size = new int[N + 1];
		partyList = new List[M];
		
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		for(int i = 0; i < M; i++) {
			partyList[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		int number = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < number; i++) {
			int element = Integer.parseInt(st.nextToken());
			union(0, element);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int partyCnt = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			
			partyList[i].add(first);
			
			for(int j = 1; j < partyCnt; j++) {
				int tgt = Integer.parseInt(st.nextToken());
				
				union(first, tgt);
			}
		}
		
		int ans = 0;
		for(int i = 0; i < M; i++) {
			if(find(partyList[i].get(0)) != 0) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa != pb) {
			if(pa == 0) {
				parent[pb] = pa;
				size[pa] += size[pb];
			} else if(pb == 0) {
				parent[pa] = pb;
				size[pb] += size[pa];
			} else {
				if(size[pa] >= size[pb]) {
					parent[pb] = pa;
					size[pa] += size[pb];
				} else {
					parent[pa] = pb;
					size[pb] += size[pa];
				}
			}
		}
	}
	
	public static int find(int x) {
		if(x == parent[x]) {
			return parent[x];
		}
		
		return parent[x] = find(parent[x]);
	}
}