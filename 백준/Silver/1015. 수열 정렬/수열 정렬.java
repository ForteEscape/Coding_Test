import java.util.*;
import java.io.*;

public class Main {
	
	static int[] data;
	static int[] tmp;
	static Deque<Integer>[] element;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		data = new int[N];
		tmp = new int[N];
		element = new Deque[1001];
		
		for(int i = 1; i <= 1000; i++) {
			element[i] = new ArrayDeque<>();
		}
		
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			tmp[i] = data[i];
		}
		
		Arrays.sort(tmp);
		
		for(int i = 0; i < N; i++) {
			element[tmp[i]].add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(element[data[i]].pollFirst()).append(" ");
		}
		
		System.out.println(sb);
	}
 }