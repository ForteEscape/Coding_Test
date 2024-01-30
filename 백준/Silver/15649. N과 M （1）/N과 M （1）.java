import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] data;
	static boolean[] visited;
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		data = new int[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			data[i] = i + 1;
		}

		solve(0, new ArrayList<>());
	}

	public static void solve(int current, List<Integer> list) {
		if (current == M) {
			StringBuilder sb = new StringBuilder();

			for (int element : list) {
				sb.append(element).append(" ");
			}

			System.out.println(sb);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				list.add(data[i]);
				visited[i] = true;
				solve(current + 1, list);
				visited[i] = false;
				list.remove(list.size() - 1);
			}
		}
	}
}
