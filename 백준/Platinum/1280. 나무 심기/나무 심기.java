import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static long[] countTree;
	static long[] distanceTree;
	static final int MAX = 200_000;
	static final int MOD = 1_000_000_007;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		countTree = new long[4 * MAX];
		distanceTree = new long[4 * MAX];
		
		long ans = 1;
		
		for(int i = 1; i <= N; i++) {
			int x = Integer.parseInt(br.readLine());
			++x;
			
			if(i == 1) {
				update(1, 1, MAX, x, 1, countTree);
				update(1, 1, MAX, x, x, distanceTree);
				continue;
			}
			
			long left = 0;
			long right = 0;
			
			left = query(1, 1, MAX, 0, x - 1, countTree) * x - query(1, 1, MAX, 0, x - 1, distanceTree);
			right = query(1, 1, MAX, x + 1, MAX, distanceTree) - query(1, 1, MAX, x + 1, MAX, countTree) * x;
			
			ans *= (left + right) % MOD;
			ans %= MOD;
			
			update(1, 1, MAX, x, 1, countTree);
			update(1, 1, MAX, x, x, distanceTree);
		}
		
		System.out.println(ans);
	}
	
	static void update(int node, int start, int end, int idx, int val, long[] tgt) {
		if(idx < start || idx > end) {
			return;
		}
		
		if(start == end) {
			tgt[node] += val;
			return;
		}
		
		int mid = (start + end) >> 1;
		if(idx <= mid) {
			update(node << 1, start, mid, idx, val, tgt);
		} else {
			update(node << 1 | 1, mid + 1, end, idx, val, tgt);
		}
		
		tgt[node] = tgt[node << 1] + tgt[node << 1 | 1];
	}
	
	static long query(int node, int start, int end, int left, int right, long[] tgt) {
		if(start > right || end < left) {
			return 0;
		}
		
		if(start >= left && end <= right) {
			return tgt[node];
		}
		
		int mid = (start + end) >> 1;
		
		return query(node << 1, start, mid, left, right, tgt) + 
				query(node << 1 | 1, mid + 1, end, left, right, tgt);
	}
}