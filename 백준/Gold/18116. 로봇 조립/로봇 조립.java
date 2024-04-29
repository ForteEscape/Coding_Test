import java.util.*;
import java.io.*;

public class Main {
	
	static int[] parents;
	static int[] size;
	static final int MAX_SIZE = 1_000_001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		parents = new int[MAX_SIZE];
		size = new int[MAX_SIZE];
		
		for(int i = 1; i < MAX_SIZE; i++) {
			parents[i] = i;
			size[i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			char opt = st.nextToken().charAt(0);
			
			if(opt == 'I') {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(find(a) != find(b)) {
					union(a, b);
				}
			} else {
				int part = Integer.parseInt(st.nextToken());
				sb.append(size[find(part)]).append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(size[pa] >= size[pb]) {
			parents[pb] = pa;
			size[pa] += size[pb];
		} else {
			parents[pa] = pb;
			size[pb] += size[pa];
		}
	}
	
	static int find(int x) {
		if(x == parents[x]) {
			return parents[x];
		}
		
		return parents[x] = find(parents[x]);
	}
}