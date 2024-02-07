import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		tree = new int[1 << (height + 1)];
		
		makeTree(1, 1, N);
		
		int idx = M - 1;
		StringBuilder sb = new StringBuilder("<");
		for(int i = 1; i <= N; i++) {
			int element = update(idx + 1, 1, 1, N);
			
			if(i < N) {
				sb.append(element).append(", ");
			} else {
				sb.append(element);
			}
			
			if(tree[1] == 0) {
				break;
			}
			
			idx += M - 1;
			idx %= tree[1];
		}
		
		sb.append(">");
		
		System.out.println(sb);
	}
	
	public static void makeTree(int node, int left, int right) {
		if(left == right) {
			tree[node] = 1;
			return;
		}
		
		int mid = (left + right) / 2;
		makeTree(node * 2, left, mid);
		makeTree(node * 2 + 1, mid + 1, right);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public static int update(int idx, int node, int left, int right) {
		tree[node]--;
		
		if(left == right) {
			return left;
		}
		
		int mid = (left + right) / 2;
		
		if(idx <= tree[node * 2]) {
			return update(idx, node * 2, left, mid);
		} else {
			return update(idx - tree[node * 2], node * 2 + 1, mid + 1, right);
		}
	}
}