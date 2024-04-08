import java.util.*;
import java.io.*;

class Main {

	static class Location {
		int y;
		int x;
		int cnt;

		Location(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static char[][] board;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		visited = new boolean[N][M];

		Location C = null;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);

				if (board[i][j] == 'C') {
					C = new Location(i, j, 1);
				}
			}
		}

		bfs(C);
	}

	static void bfs(Location C) {
		Deque<Location> queue = new ArrayDeque<>();
		queue.addLast(C);
		visited[C.y][C.x] = true;

		while (!queue.isEmpty()) {
			Location cur = queue.pollFirst();

			if (board[cur.y][cur.x] == 'B') {
				System.out.println(cur.cnt - 1);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (isUnreachable(ny, nx) || visited[ny][nx] || board[ny][nx] == '*') {
					continue;
				}

				visited[ny][nx] = true;
				queue.addLast(new Location(ny, nx, cur.cnt + 1));
			}
		}
	}

	static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}