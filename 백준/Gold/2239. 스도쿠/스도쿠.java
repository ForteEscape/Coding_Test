import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Pair {
		int y;
		int x;

		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int[][] board;
	static Set<Integer>[] row;
	static Set<Integer>[] col;
	static Set<Integer>[][] area;
	static List<Pair> undeterminPair;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[9][9];

		row = new Set[9];
		col = new Set[9];
		area = new Set[3][3];
		undeterminPair = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			row[i] = new HashSet<>();
			col[i] = new HashSet<>();
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				area[i][j] = new HashSet<>();
			}
		}

		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				int element = line.charAt(j) - '0';
				
				if (element != 0) {
					board[i][j] = element;
					row[i].add(element);
					col[j].add(element);
					area[i / 3][j / 3].add(element);
				} else {
					undeterminPair.add(new Pair(i, j));
				}
			}
		}

		solve(0);
	}

	static void solve(int cur) {
		if (cur == undeterminPair.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}

			System.exit(0);
		}

		Pair currentPair = undeterminPair.get(cur);

		for (int i = 1; i <= 9; i++) {
			if (isAddable(i, currentPair)) {
				board[currentPair.y][currentPair.x] = i;
				row[currentPair.y].add(i);
				col[currentPair.x].add(i);
				area[currentPair.y / 3][currentPair.x / 3].add(i);

				solve(cur + 1);

				col[currentPair.x].remove(i);
				row[currentPair.y].remove(i);
				area[currentPair.y / 3][currentPair.x / 3].remove(i);
				board[currentPair.y][currentPair.x] = 0;
			}
		}
	}

	static boolean isAddable(int x, Pair currentPair) {
		return !(row[currentPair.y].contains(x) || col[currentPair.x].contains(x)
				|| area[currentPair.y / 3][currentPair.x / 3].contains(x));
	}

}