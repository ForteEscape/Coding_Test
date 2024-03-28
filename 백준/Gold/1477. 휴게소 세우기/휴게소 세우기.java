import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());

		int[] diff = new int[N + 1];
		int[] data = new int[N + 2];
		int start = 1;
		int end = Integer.MIN_VALUE;

		if (N > 0) {
			st = new StringTokenizer(br.readLine());
		}

		data[0] = 0;
		data[N + 1] = goal;
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(data);

		for (int i = 0; i < N + 1; i++) {
			diff[i] = data[i + 1] - data[i];
			end = Math.max(end, diff[i]);
		}

		while (start < end) {
			int cnt = 0;
			int mid = (start + end) >> 1;
		
			for (int i = 0; i < diff.length; i++) {
				int tmp = 0;
				if (diff[i] > mid) {
					if (diff[i] % mid == 0) {
						tmp = (diff[i] / mid) - 1;
					} else {
						tmp = diff[i] / mid;
					}
				}
				cnt += tmp;
			}
			
			if (cnt <= M) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(end);
	}
}