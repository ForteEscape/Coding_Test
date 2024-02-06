import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class SegTree {
		int size;
		int height;
		long[] treeData;
		
		SegTree(int N) {
			height = (int) Math.ceil(Math.log(N) / Math.log(2));
			size = (1 << (height + 1));
			
			treeData = new long[size];
		}
		
		public long makeTree(int[] data, int node, int start, int end) {
			if(start == end) {
				return treeData[node] = data[start]; 
			}
			
			int mid = (start + end) / 2;
			treeData[node] += makeTree(data, node * 2, start, mid);
			treeData[node] += makeTree(data, node * 2 + 1, mid + 1, end);
			
			return treeData[node];
		}
		
		// start, end는 현재 시작 범위와 끝범위, left, right는 쿼리에서 들어온 시작 범위와 끝 범위
		public long getValue(int node, int start, int end, int left, int right) {
			
			// 범위에서 완전히 벗어나는 경우
			if(left > end || right < start) {
				return 0;
			}
			
			if(left <= start && end <= right) {
				return treeData[node];
			}
			
			int mid = (start + end) / 2;
			
			return getValue(node * 2, start, mid, left, right) + 
					getValue(node * 2 + 1, mid + 1, end, left, right);
		}
		
		public void updateValue(int node, int start, int end, int idx, int diff) {
			if(idx < start || idx > end) return;
			treeData[node] = treeData[node] + diff;
			
			if(start != end) {
				int mid = (start + end) / 2;
				updateValue(node * 2, start, mid, idx, diff);
				updateValue(node * 2 + 1, mid + 1, end, idx, diff);
				treeData[node] = treeData[node * 2] + treeData[node * 2 + 1];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		long[] pay = new long[N + 1];
		
		SegTree tree = new SegTree(N);
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			
			if(type == 1) {
				int x = Integer.parseInt(st.nextToken());
				
				tree.updateValue(1, 1, N, day, x);
				pay[day] = pay[day] + x;
			} else {
				int end = Integer.parseInt(st.nextToken());
				System.out.println(tree.getValue(1, 1, N, day, end));
			}
		}
	}
}