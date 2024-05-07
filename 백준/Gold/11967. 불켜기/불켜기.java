import java.util.*;
import java.io.*;

public class Main {
	
	static class Field {
		int y;
		int x;
		
		Field(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Field other = (Field) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	static boolean[][] visited;
	static int N, M;
	static List<Field>[][] graph;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static Set<Field> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new List[N + 1][N + 1];
		set = new HashSet<>();
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				graph[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[y][x].add(new Field(a, b));
		}
		
		set.addAll(graph[1][1]);
		set.add(new Field(1, 1));
		int size = set.size();
		int curSize = -1;
		
		while(true) {
			bfs();
			curSize = set.size();
			
			if(size == curSize) {
				break;
			}
			
			size = curSize;
		}
		
		System.out.println(curSize);
	}
	
	static void bfs() {
		visited = new boolean[N + 1][N + 1];
		visited[1][1] = true;
		Deque<Field> queue = new ArrayDeque<>();
		queue.addLast(new Field(1, 1));
		
		while(!queue.isEmpty()) {
			Field cur = queue.pollFirst();
			
			set.addAll(graph[cur.y][cur.x]);
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				Field next = new Field(nx, ny);
				
				if(isUnreachable(ny, nx) || visited[ny][nx] || !set.contains(next)) {
					continue;
				}
				
				visited[ny][nx] = true;
				queue.addLast(next);
			}
		}
	}
	
	static boolean isUnreachable(int y, int x) {
		return y < 1 || y > N || x < 1 || x > N;
	}
}