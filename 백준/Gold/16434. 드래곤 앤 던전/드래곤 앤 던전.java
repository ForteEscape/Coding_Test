import java.util.*;
import java.io.*;

public class Main {
	
	static class Room {
		int type;
		int atk;
		int hp;
		
		Room(int type, int atk, int hp) {
			this.type = type;
			this.atk = atk;
			this.hp = hp;
		}
	}
	
	static List<Room> dungeon;
	static int N, initAtk;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		initAtk = Integer.parseInt(st.nextToken());
		
		dungeon = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			int atk = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			
			dungeon.add(new Room(type, atk, hp));
		}
		
		long start = 1;
		long end = 9_000_000_000_000_000_001L;
		long ans = Long.MAX_VALUE;
		
		while(start <= end) {
			long mid = (start + end) >> 1;
		
			if(simul(mid)) {
				ans = Math.min(ans, mid);
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean simul(long initHp) {
		long currentAtk = initAtk;
		long currentHp = initHp;
		
		for(int i = 0; i < N; i++) {
			Room currentRoom = dungeon.get(i);
			
			if(currentRoom.type == 2) {
				currentAtk += currentRoom.atk;
				currentHp = Math.min(initHp, currentHp + currentRoom.hp);
			} else {
				int mobHp = currentRoom.hp;
				int mobAtk = currentRoom.atk;
				
				long hitCount = mobHp % currentAtk == 0 ? mobHp / currentAtk : (mobHp / currentAtk) + 1;
				
				currentHp -= (mobAtk * (hitCount - 1));
				
				if(currentHp <= 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}