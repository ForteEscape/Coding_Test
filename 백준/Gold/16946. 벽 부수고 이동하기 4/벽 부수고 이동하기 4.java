import java.util.*;
import java.io.*;

public class Main {

	static int N, M, moveCount, region;
	static int[][] field;
	static HashMap<Integer, Integer> regionCount;
	static Queue<Location> nextLocation;

	static class Location {
		int row;
		int col;

		public Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	// 상 우 하 좌
	static final int[] dR = { -1, 0, 1, 0 };
	static final int[] dC = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 선언부
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		field = new int[N][M];
		regionCount = new HashMap<>();
		region = 1;

		// 배열 입력받기..단, 벽은 '1 -> -1' 로 변경
		for (int row = 0; row < N; row++) {
			String str = br.readLine();
			for (int col = 0; col < M; col++) {
				field[row][col] = str.charAt(col) - '0';
				if (field[row][col] == 1)
					field[row][col] = -1;
			}
		}

		// 서로 인접한 0들끼리 같은 "region"으로 묶는다
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (field[row][col] == 0)
					fillRegion(row, col);
			}
		}
		
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(field[i]));
//		}

		// 탐색을 하며, 벽인 경우 인접한 regionCount를 더해주고, 아닌 경우 0을 저장한다
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (field[row][col] == -1)
					sb.append(countMove(row, col));
				else
					sb.append(0);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	// countMove : 인접한 region들의 멤버 수를 합산
	private static int countMove(int startRow, int startCol) {
		int ret = 1;
		Set<Integer> regionNearBy = new HashSet<>();
		for (int dir = 0; dir < 4; dir++) {
			int candRow = startRow + dR[dir];
			int candCol = startCol + dC[dir];

			if (!outOfBound(candRow, candCol) && field[candRow][candCol] != -1) {
				int curRegion = field[candRow][candCol];
				if (!regionNearBy.contains(curRegion)) {
					regionNearBy.add(curRegion);
					ret += regionCount.get(curRegion);
				}
			}
		}

		return ret % 10;
	}

	// fillRegion : region별로 나누고, region 멤버수를 구한 다음 hashset에 저장
	private static void fillRegion(int startRow, int startCol) {
		nextLocation = new LinkedList<>();

		nextLocation.add(new Location(startRow, startCol));
		int curCount = 1;
		field[startRow][startCol] = region;

		while (!nextLocation.isEmpty()) {
			Location curLocation = nextLocation.poll();
			int curRow = curLocation.row;
			int curCol = curLocation.col;

			for (int dir = 0; dir < 4; dir++) {
				int candRow = curRow + dR[dir];
				int candCol = curCol + dC[dir];

				if (!outOfBound(candRow, candCol) && field[candRow][candCol] == 0) {
					field[candRow][candCol] = region;
					nextLocation.add(new Location(candRow, candCol));
					curCount++;
				}
			}
		}

		regionCount.put(region, curCount);
		region++;
	}

	private static boolean outOfBound(int row, int col) {
		if (row < 0 || col < 0 || row >= N || col >= M)
			return true;
		else
			return false;
	}

}