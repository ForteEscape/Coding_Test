import java.util.*;
import java.io.*;

public class Main {
	
	static class Link {
		int from;
		int to;
		
		Link(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	static Link[] link;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		link = new Link[N];
		tree = new long[4 * N];
		
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			link[i - 1] = new Link(i, num);
		}
		
		Arrays.sort(link, Comparator.comparingInt(e -> e.to));
		
		long ans = 0;
		for(int i = 0; i < N; i++) {
			long res = query(1, 1, N, link[i].from + 1, N);
			update(1, 1, N, link[i].from, 1);
			
			ans += res;
		}
		
		System.out.println(ans);
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
		
		tree[node] = tree[node << 1] + tree[node << 1| 1];
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
				query(node << 1 | 1, mid + 1, end, left, right);
	}
}