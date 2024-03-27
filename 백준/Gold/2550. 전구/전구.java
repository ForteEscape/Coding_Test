import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Pair {
		int num;
		int idx;

		Pair(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}

		@Override
		public String toString() {
			return "Pair [num=" + num + ", idx=" + idx + "]";
		}

	}

	static int[] line;
	static int[] lis;
	static Map<Integer, Integer> map;
	static Pair[] track;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		map = new HashMap<>();
		track = new Pair[N];

		int[] switchArr = new int[N];
		int[] bulbArr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int element = Integer.parseInt(st.nextToken());
			switchArr[i] = element;
			map.put(element, i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			bulbArr[i] = map.get(Integer.parseInt(st.nextToken()));
		}

		lis = new int[N];
		lis[0] = bulbArr[0];
		track[0] = new Pair(bulbArr[0], 0);

		int idx = 0;

		for (int i = 1; i < N; i++) {
			if (lis[idx] < bulbArr[i]) {
				lis[++idx] = bulbArr[i];
				track[i] = new Pair(bulbArr[i], idx);
			} else {
				int tmp = binarySearch(0, idx, bulbArr[i]);
				lis[tmp] = bulbArr[i];
				track[i] = new Pair(bulbArr[i], tmp);
			}
		}
		
		List<Integer> ansList = new ArrayList<>();
		int key = idx;
		
		for(int i = N - 1; i >= 0; i--) {
			if(track[i].idx == key) {
				ansList.add(switchArr[track[i].num]);
				key--;
			}
		}
		
		Collections.sort(ansList);
		
		StringBuilder sb = new StringBuilder();
		sb.append(idx + 1).append("\n");		
		for(int element: ansList) {
			sb.append(element).append(" ");
		}
		
		System.out.println(sb);
	}

	static int binarySearch(int start, int end, int key) {
		int left = start;
		int right = end;

		while (right > left) {
			int mid = (left + right) >> 1;

			if (lis[mid] > key) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return right;
	}
}