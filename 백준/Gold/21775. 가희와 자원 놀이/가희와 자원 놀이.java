import java.io.*;
import java.util.*;

public class Main {

	static class Operation {
		int id;
		String type;
		int resources;

		Operation(int id, String type, int resources) {
			this.id = id;
			this.type = type;
			this.resources = resources;
		}
	}

	static class Player {
		Operation currentOperation;
		Set<Integer> currentResources;

		Player() {
			currentOperation = null;
			currentResources = new HashSet<>();
		}
	}

	static int N, T;
	static int[] turn;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		turn = new int[T];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < T; i++) {
			turn[i] = Integer.parseInt(st.nextToken());
		}

		Deque<Operation> queue = new ArrayDeque<>();
		Set<Integer> usingSet = new HashSet<>();
		Player[] player = new Player[N + 1];

		for (int i = 1; i <= N; i++) {
			player[i] = new Player();
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());

			int id = Integer.parseInt(st.nextToken());
			String type = st.nextToken();
			int resources = -1;

			if (type.equals("acquire") || type.equals("release")) {
				resources = Integer.parseInt(st.nextToken());
			}

			queue.addLast(new Operation(id, type, resources));
		}

		int currentTurn = 0;
		while (currentTurn < T) {
			int currentPlayer = turn[currentTurn];

			if (player[currentPlayer].currentOperation == null) {
				player[currentPlayer].currentOperation = queue.pollFirst();
			} 
			
			System.out.println(player[currentPlayer].currentOperation.id);

			if (player[currentPlayer].currentOperation.type.equals("next")) {
				player[currentPlayer].currentOperation = null;
			} else if (player[currentPlayer].currentOperation.type.equals("acquire")) {
				int resources = player[currentPlayer].currentOperation.resources;
				
				if (!usingSet.contains(resources)) {
					player[currentPlayer].currentResources.add(resources);
					usingSet.add(resources);
					player[currentPlayer].currentOperation = null;
				}
			} else if (player[currentPlayer].currentOperation.type.equals("release")) {
				int resources = player[currentPlayer].currentOperation.resources;

				player[currentPlayer].currentResources.remove(resources);
				usingSet.remove(resources);
				
				player[currentPlayer].currentOperation = null;
			}
			
			currentTurn++;
		}
	}
}