import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Location {
		int y;
		int x;

		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	// 파이프를 가장 많이 꽂기 위해서는 1열 x행에 존재하는 파이프가 최대한 위쪽으로 배치하는게 유리할 것이다.
	static int[] dy = { -1, 0, 1 };
	static int[] dx = { 1, 1, 1 };
	static int N, M;
	static char[][] board;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j);
			}
		}

		visited = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (dfs(new Location(i, 0))) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	public static boolean dfs(Location cur) {
		visited[cur.y][cur.x] = true;
		if (cur.x == M - 1) {
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int ny = cur.y + dy[i];
			int nx = cur.x + dx[i];

			if (isUnreachable(ny, nx) || board[ny][nx] == 'x' || visited[ny][nx]) {
				continue;
			}

			if (dfs(new Location(ny, nx))) {
				return true;
			}
		}

		return false;
	}

	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}