import java.util.*;
import java.io.*;

public class Main {
	
	static class Data implements Comparable<Data> {
		int num;
		int idx;
		int cnt;
		
		Data(int num, int idx, int cnt) {
			this.num = num;
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Data o) {
			if(Integer.compare(this.cnt, o.cnt) == 0) {
				return Integer.compare(this.idx, o.idx);
			} else {
				return -Integer.compare(this.cnt, o.cnt);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		Map<Integer, Data> map = new HashMap<>();
		List<Data> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int element = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(element)) {
				map.put(element, new Data(element, i, 1));
			} else {
				map.get(element).cnt += 1;
			}
		}
		
		for(int key : map.keySet()) {
			list.add(map.get(key));
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.get(i).cnt; j++) {
				sb.append(list.get(i).num).append(" ");
			}
		}
		System.out.println(sb);
	}
}