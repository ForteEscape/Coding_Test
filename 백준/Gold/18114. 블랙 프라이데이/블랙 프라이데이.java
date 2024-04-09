import java.util.*;
import java.io.*;

class Main {

	static int N, C;
	static List<Integer> data;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		data = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			data.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(data, Comparator.comparingInt(e -> -e));
		
		binarySearch(0, N - 1, C);
		
		for(int i = 0; i < N - 1; i++) {
			binarySearch(i + 1, N - 1, C - data.get(i));
		}
		
		for(int i = 0; i < N - 2; i++) {
			for(int j = i + 1; j < N - 1; j++) {
				binarySearch(j + 1, N - 1, C - data.get(i) - data.get(j));
			}
		}
		
		System.out.println(0);
	}
	
	static void binarySearch(int start, int end, int val) {
		while(start <= end) {
			int mid = (start + end) >> 1;
		
			if(data.get(mid) < val) {
				end = mid - 1;
			} else if(data.get(mid) == val) {
				System.out.println(1);
				System.exit(0);
			} else {
				start = mid + 1;
			}
		}
	}
}