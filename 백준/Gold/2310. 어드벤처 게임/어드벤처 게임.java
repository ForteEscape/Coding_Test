import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Room {
		char type;
		int amount;
		
		Room(char type, int amount) {
			this.type = type;
			this.amount = amount;
		}
	}
	
	static class Player {
		int coin;
		
		Player() {
			this.coin = 0;
		}
	}
	
	static boolean[] visited;
	static Room[] rooms;
	static List[] graph;
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) {
				break;
			}
			
			visited = new boolean[N + 1];
			rooms = new Room[N + 1];
			graph = new ArrayList[N + 1];
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				char type = st.nextToken().charAt(0);
				int amount = Integer.parseInt(st.nextToken());
				
				rooms[i] = new Room(type, amount);
				
				for(int j = 0;;j++) {
					int to = Integer.parseInt(st.nextToken());
					
					if(to == 0) break;
					graph[i].add(to);
				}
			}
			
			boolean flag = bfs(1, new Player());
			
			if(flag) {
				sb.append("Yes").append("\n");
			} else {
				sb.append("No").append("\n");
			}
		}
		System.out.println(sb);
	}
	
	public static boolean bfs(int startNode, Player p) {
		Deque<Integer> queue = new ArrayDeque<>();
		
		if(rooms[startNode].type == 'T' && p.coin < rooms[startNode].amount) {
			return false;
		} else if(rooms[startNode].type == 'L') {
			p.coin = Math.max(p.coin, rooms[startNode].amount);
		}
		
		queue.addLast(startNode);
		visited[startNode] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.pollFirst();
			
			if(cur == N) {
				return true;
			}
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int adjNode = (Integer) graph[cur].get(i);
				
				if(visited[adjNode]) continue;
				
				if(rooms[adjNode].type == 'T' && p.coin < rooms[adjNode].amount) {
					continue;
				} else if(rooms[adjNode].type == 'T' && p.coin >= rooms[adjNode].amount) {
					p.coin -= rooms[adjNode].amount;
				} else if(rooms[adjNode].type == 'L') {
					p.coin = Math.max(p.coin, rooms[adjNode].amount);
				}
				
				visited[adjNode] = true;
				queue.addLast(adjNode);
			}
		}
		
		return false;
	}
}