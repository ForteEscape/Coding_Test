import java.io.*;
import java.util.*;

public class Main {

	static class Location {
		int y;
		int x;

		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N, M;
	static List<Location> house;
	static List<Location> chicken;
	static List<Set<Location>> combination;
	static int[][] board;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		house = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == 1) {
					house.add(new Location(i, j));
				} else if(board[i][j] == 2) {
					chicken.add(new Location(i, j));
				}
			}
		}
		
		combination = new ArrayList<>();
		ans = Integer.MAX_VALUE;
		solve(0, new HashSet<>(), M);
		
		for(int i = 0; i < combination.size(); i++) {
			int result = 0;
			for(Location home : house) {
				int minDist = Integer.MAX_VALUE;
				for(Location element : combination.get(i)) {
					minDist = Math.min(minDist, Math.abs(home.y - element.y) + Math.abs(home.x - element.x));
				}
				result += minDist;
			}
			ans = Math.min(ans, result);
		}
		
		System.out.println(ans);
		// nCr
	}
	
	public static void solve(int idx, Set<Location> set, int remain) {
		if(remain == 0) {
			HashSet<Location> result = new HashSet<>();
			for(Location element : set) {
				result.add(element);
			}
			
			combination.add(result);
			return;
		}
		
		if(idx == chicken.size()) {
			return;
		}
		
		set.add(chicken.get(idx));
		solve(idx + 1, set, remain - 1);
		set.remove(chicken.get(idx));
		solve(idx + 1, set, remain);
	}

	public static boolean isUnreachable(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}