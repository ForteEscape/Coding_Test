import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static StringTokenizer st;
	static String[] tree;
	static boolean flag;
	static Set<String> operation;
	static String first;
	
	static {
		operation = new HashSet<>(Arrays.asList("+", "*", "-", "/"));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			N = Integer.parseInt(br.readLine());
			tree = new String[N + 1];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				String num = st.nextToken();
				String code = st.nextToken();
				tree[i] = code;
			}
			
			first = "";
			flag = true;
			travel(1);
			
			if(!flag) { 
				System.out.println("#" + test_case + " 0");
			} else {
				System.out.println("#" + test_case + " 1");
			}
		}
	}
	
	public static void travel(int startNode) {
		if(startNode > N) {
			return;
		}
		
		travel(startNode * 2);
		
		if(first.equals("")) {
			if(operation.contains(tree[startNode])) {
				flag = false;
				return;
			} else {
				first = tree[startNode];
			}
		} else {
			if(operation.contains(tree[startNode])) { // 부호라면
				if(operation.contains(first)) {
					flag = false;
					return;
				} else {
					first = tree[startNode];
				}
			} else {
				if(operation.contains(first)) {
					first = tree[startNode];
				} else {
					flag = false;
					return;
				}
			}
		}
		
		if(!flag) { 
			return;
		}
		
		travel(startNode * 2 + 1);
		
		if(!flag) { 
			return;
		}
	}
}