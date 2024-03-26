import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Location implements Comparable<Location> {
		int y;
		int x;
		int loss;

		Location(int y, int x, int loss) {
			this.y = y;
			this.x = x;
			this.loss = loss;
		}

		@Override
		public int compareTo(Location o) {
			return this.loss - o.loss;
		}
	}

	static int[][] board;
	static int[][] weight;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static final int INF = Integer.MAX_VALUE;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int idx = 1;

		while (true) {
			N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;

			board = new int[N][N];
			weight = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					weight[i][j] = INF;
				}
			}

			PriorityQueue<Location> pq = new PriorityQueue<>();
			pq.offer(new Location(0, 0, board[0][0]));

			while (!pq.isEmpty()) {
				Location cur = pq.poll();
				
				if(weight[cur.y][cur.x] < cur.loss) {
					continue;
				}

				for (int i = 0; i < 4; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];

					if (isUnreachable(ny, nx)) {
						continue;
					}
					
					if(weight[ny][nx] > cur.loss + board[ny][nx]) {
						weight[ny][nx] = cur.loss + board[ny][nx];
						pq.offer(new Location(ny, nx, cur.loss + board[ny][nx]));
					}
				}
			}
			
			sb.append("Problem ").append(idx).append(": ").append(weight[N - 1][N - 1]).append("\n");

			idx++;
		}
		
		System.out.print(sb);
	}

	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}