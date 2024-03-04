import java.util.*;
import java.io.*;

public class Main {

	static class Location {
		int y;
		int x;
		int direction;
		int speed;
		int mess;

		Location(int y, int x, int direction, int speed, int mess) {
			this.y = y;
			this.x = x;
			this.direction = direction;
			this.speed = speed;
			this.mess = mess;
		}
	}

	static int N, M, K;
	static List<Location> fireBalls;
	static int[][] board;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Map<Integer, List<Location>> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		fireBalls = new ArrayList<>();
		board = new int[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int mess = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			fireBalls.add(new Location(y - 1, x - 1, direction, speed, mess));
		}
		
		map = new HashMap<>();

		for (int i = 0; i < K; i++) {
			move();
			sum();
		}
		
		int ans = 0;
		for(Location element : fireBalls) {
			ans += element.mess;
		}
		
		System.out.println(ans);
	}

	public static void move() {
		map.clear();
		
		for (Location fireball : fireBalls) {
			fireball.y = (fireball.y + ((dy[fireball.direction] * fireball.speed) % N) + N) % N;
			fireball.x = (fireball.x + ((dx[fireball.direction] * fireball.speed) % N) + N) % N;

			if (!map.containsKey(fireball.y * N + fireball.x)) {
				map.put(fireball.y * N + fireball.x, new ArrayList<>());
			}

			map.get(fireball.y * N + fireball.x).add(fireball);
		}

		fireBalls.clear();
	}

	public static void sum() {
		for (int key : map.keySet()) {
			if (map.get(key).size() >= 2) {
				divide(map.get(key));
			} else {
				fireBalls.add(map.get(key).get(0));
			}
		}
	}

	public static void divide(List<Location> list) {
		int[] cnt = new int[2];
		int totalMess = 0;
		int totalSpeed = 0;
		int[] direction = new int[4];
		int y = -1;
		int x = -1;

		for (Location element : list) {
			totalMess += element.mess;
			totalSpeed += element.speed;
			
			y = element.y;
			x = element.x;
			
			cnt[element.direction % 2]++;
		}
		
		if((totalMess / 5) == 0) {
			return;
		}

		int idx = 0;
		if (cnt[0] == 0 || cnt[1] == 0) {
			for (int i = 0; i <= 6; i += 2) {
				direction[idx++] = i;
			}
		} else {
			for (int i = 1; i <= 7; i += 2) {
				direction[idx++] = i;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			fireBalls.add(new Location(y, x, direction[i], (totalSpeed / list.size()), (totalMess / 5)));
		}
	}
}