import java.util.*;
import java.io.*;

public class Main {
	
	static class Link implements Comparable<Link>{
		int from;
		int to;
		
		Link(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Link o) {
			// TODO Auto-generated method stub
			if(this.from == o.from) {
				return Integer.compare(this.to, o.to);
			}
			
			return Integer.compare(this.from, o.from);
		}
	}
	
	static long[] tree;
	static int N, M, K;
	static Link[] link;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			tree = new long[4 * M];
			link = new Link[K];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				link[i] = new Link(a, b);
			}
			
			Arrays.sort(link);
			long ans = 0L;
			
			for(int i = 0; i < K; i++) {
				long res = query(1, 1, M, link[i].to + 1, M);
				update(1, 1, M, link[i].to, 1);
				
				ans += res;
			}
			
			sb.append("Test case ").append(t).append(": ").append(ans).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void update(int node, int start, int end, int idx, int val) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			tree[node] += val;
			return;
		}
		
		int mid = (start + end) >> 1;
		
		if(idx <= mid) {
			update(node << 1, start, mid, idx, val);
		} else {
			update(node << 1 | 1, mid + 1, end, idx, val);
		}
		
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
	
	static long query(int node, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return 0L;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) >> 1;
		
		return query(node << 1, start, mid, left, right) + 
				query(node << 1 | 1, mid +1, end, left, right);
	}
	
}