import java.io.*;
import java.util.*;

public class Main {

	static class Num implements Comparable<Num>{
		int element;
		int idx;

		Num(int element, int idx) {
			this.element = element;
			this.idx = idx;
		}

		@Override
		public int compareTo(Num o) {
			// TODO Auto-generated method stub
			if(o.element == this.element) {
				if(this.idx < o.idx) {
					return -1;
				} else {
					return 1;
				}
			} else if(o.element > this.element) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	static TreeSet<Num> treeSet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int Q = Integer.parseInt(br.readLine());
			StringTokenizer st;

			treeSet = new TreeSet<>();

			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());

				char query = st.nextToken().charAt(0);
				int element = Integer.parseInt(st.nextToken());

				if (query == 'I') {
					int idx = i;
					treeSet.add(new Num(element, idx));
				} else if(query == 'D') {
					if(treeSet.isEmpty()) continue;
					if(element > 0) {
						treeSet.pollFirst();
					} else {
						treeSet.pollLast();
					}
				}
			}
			
			if(treeSet.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.println(treeSet.first().element + " " + treeSet.last().element);
			}
		}
	}
}